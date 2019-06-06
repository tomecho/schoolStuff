package factory

//import adapter.ProgramParserAdapter
//import bc.{ByteCodeFactory, ByteCodeParser, ByteCodes, MyByteCodeParser}
//import vendor.{ProgramParser, VendorProgramParser}
//import vm.{VirtualMachineParser, MyVirtualMachine, VirtualMachine}

import bc.{ByteCodeFactory, ByteCodeParser}
import vendor.{ProgramParser}
import vm.{VirtualMachineParser, VirtualMachine}

/**
 * The `VirtualMachineFactory` follows the *factory pattern*. It provides
 * methods for each of the important parts in this assignment. You must
 * implement each method such that it returns an object of the correct type.
 */
object VirtualMachineFactory { //like the insructers comment, there isnt much going on her
  // TODO
  def byteCodeFactory: ByteCodeFactory = new MyByteCodeFactory()
  // TODO
  def vendorParser: ProgramParser = new MyProgramParser()
  // TODO
  def byteCodeParser: ByteCodeParser = new MyByteCodeParser()
  // TODO
  def virtualMachineParser: VirtualMachineParser = new MyVirtualMachineParser()
  // TODO
  def virtualMachine: VirtualMachine = new MyVirtualMachine()
}
