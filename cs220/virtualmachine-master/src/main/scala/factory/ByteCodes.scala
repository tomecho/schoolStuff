package factory

import bc.{InvalidBytecodeException, ByteCode}
import vm.VirtualMachine

/**
 * Created by tom on 10/25/15.
 */
  class iconst(NUM: Int) extends ByteCode{ //looks like you did these comments for me
    //    NUM: The iconst instruction pushes the integer value NUM on the virtual machine stack. .
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = {
      //if(NUM == null) throw new InvalidBytecodeException("no param on iconst")
      vm.push(NUM)
    }
  }
  class iadd extends ByteCode{
    //iadd: The iadd instruction pops the top two values from the virtual machine stack and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(vm.pop()._1 + vm.pop()._1)
  }
  class isub extends ByteCode{
    //  isub: The isub instruction pops the top two values from the virtual machine stack and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(vm.pop()._1 - vm.pop()._1)
  }
  class imul extends ByteCode{
    //  imul: The imul instruction pops the top two values from the virtual machine stack and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(vm.pop()._1 * vm.pop()._1)
  }
  class idiv extends ByteCode{
    //The idiv instruction pops the top two values from the virtual machine stack and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(vm.pop()._1 / vm.pop()._1)
  }
  class irem extends ByteCode{
    //The irem instruction pops the top two values from the virtual machine stack and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(vm.pop()._1 % vm.pop()._1)
  }
  class ineg extends ByteCode{
    //The ineg instruction pops the the top value from the virtual machine stack, negates it, and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(-vm.pop()._1)
  }
  class iinc extends ByteCode{
    //The iinc instruction pops the the top value from the virtual machine stack
  //, increments it, and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = vm.push(vm.pop()._1+1)
  }
  class idec extends ByteCode{
    //The idec instruction pops the the top value from the virtual machine stack
    //, decrements it, and pushes the result.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm: VirtualMachine): VirtualMachine = vm.push(vm.pop()._1 - 1)
  }
  class iswap extends ByteCode{
    //    iswap: The iswap instruction pops the top two values from the virtual machine stack and pushes them in the opposite order - effectively swapping the top two values on the top of the stack.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = {
      val x = vm.pop()._1
      val y = vm.pop()._1
      vm.push(x)
      vm.push(y)
    }
  }
  class idup extends ByteCode{
    //The idup instruction pops the top value from the stack and pushes it twice onto the stack(duplicates the top value).
    val code: Byte = bytecode(this.getClass.getSimpleName)
    override def execute(vm : VirtualMachine): VirtualMachine = {
      val x = vm.pop()._1
      vm.push(x)
      vm.push(x)
    }
  }
  class print extends ByteCode{
    //The print instruction pops the top value from the stack and prints the value to the console.
    val code: Byte = bytecode(this.getClass.getSimpleName)
    def execute(vm : VirtualMachine): VirtualMachine = {
      val x = vm.pop()
      println(x._1)
      return x._2
    }
  }