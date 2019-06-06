package factory

import java.util
import java.util.Stack
import bc.{InvalidBytecodeException, ByteCode, ByteCodeParser}
import vm.{MachineUnderflowException, VirtualMachine}
/**
 * Created by tom on 10/26/15.
 */

/**
 * Represents a stack-based virtual machine.
 *
 * An implementation of a virtual machine is capable of executing
 * a vector of [[bc.ByteCode]] values. This is where the command
 * pattern comes into play! That is, we defer the execution of
 * each bytecode until it is executed by the virtual machine.
 *
 * You will need to implement one of these!
 */

class MyVirtualMachine extends VirtualMachine{
  var stack = new Stack[Int]()
  /**
   * Executes a vector of bytecodes.
   *
   * Note, that this is an "immutable" object. That is, it
   * will return a new virtual machine after executing each
   * of the bytecode objects in the vector.
   *
   * @param bc a vector of bytecodes
   * @return a new virtual machine
   */
  def execute(bc: Vector[ByteCode]): VirtualMachine = {
    for(n <- bc.iterator){
      println(state)
      executeOne(Vector[ByteCode](n))
    }
    this //just call execute one for each
  }

  /**
   * Executes the next bytecode in the vector of bytecodes.
   *
   * This method only executes a single byte code in the vector of bytecodes.
   * It returns a tuple of the new vector of bytecodes (with the executed
   * bytecode removed) and the new virtual machine.
   *
   * You may assume that `bc` contains at least 1 bytecode.
   *
   * @param bc the vector of bytecodes
   * @return a tuple of a new vector of bytecodes and virtual machine
   */
  def executeOne(bc: Vector[ByteCode]): (Vector[ByteCode], VirtualMachine) = {
    if(bc.size >= 1) bc.head.execute(this)
    else throw new MachineUnderflowException("not enough stuff to execute")
    return (bc.tail, this) //execute the first, i checked bc.size anyways
  }

  /**
   * Pushes an integer value onto the virtual machine stack.
   *
   * @param value the integer to push
   * @return a new virtual machine with the integer `value` pushed
   */
  def push(value: Int): VirtualMachine = {
    stack.push(value)
    this //such simplicity
  }

  /**
   * Pops an integer value off of the virtual machine stack.
   *
   * @return (i, vm), where i is the integer popped and vm is the
   *         new virtual machine
   */
  def pop(): (Int, VirtualMachine) = {
    if(stack.size < 1)throw new MachineUnderflowException("not enough objects in the stack to execute")
    return (stack.pop(), this) //another simple one
  }

  /**
   * Returns the state of the virtual machine stack.
   *
   * The value at index 0 is the value on the top of the stack.
   *
   * @return the state of the stack
   */
  def state: Vector[Int] = {
    var out = Vector[Int]() //iteratre thru the stack
    for(i <- 0 until stack.size){
      out = out :+ stack.get(i)
    }
    return out.reverse //looks like i need to do this the other way, quick fix
  }
}