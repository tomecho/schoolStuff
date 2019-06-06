package factory

import vm.VirtualMachine
import bc.ByteCodeValues

/**
 * Created by tom on 10/23/15.
 */
/**
 * [[bc.ByteCode]] is a trait that represents a bytecode operation that can
 * be executed by a virtual machine. We use the *command* pattern here
 * to execute the bytecode operation. A bytecode must have a code (a
 * unique byte representing the bytecode - see [[ByteCodeValues.bytecode]])
 * and a method that executes the bytecode operation on the given
 * virtual machine.
 *
 * This trait extends the [[ByteCodeValues]] trait to give an
 * implementation access to the definitions provided by the
 * that trait.
 */
class MyByteCode extends ByteCodeValues {
  /**
   * A unique byte value representing the bytecode. An implementation
   * will set this to the bytecode corresponding to the name of the
   * bytecode in [[ByteCodeValues]]
   */
  val code: Byte

  /**
   * Returns a new [[VirtualMachine]] after executing this bytecode operation.
   * @param vm the initial virtual machine
   * @return a new virtual machine
   */
  def execute(vm : VirtualMachine): VirtualMachine = {
    vm.execute(code)
  }
}