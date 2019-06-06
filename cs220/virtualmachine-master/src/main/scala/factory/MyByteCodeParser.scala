package factory

import java.lang.Exception
import java.lang.Exception
import bc.{ByteCodeValues, InvalidBytecodeException, ByteCode, ByteCodeParser}

import scala.util.control.Exception

/**
 * Created by tom on 10/22/15.
 */
class MyByteCodeParser extends ByteCodeParser with ByteCodeValues{
  /**
   * Parses a vector of `Byte` into a vector of `ByteCode`.
   *
   * You should use [[bc.ByteCodeValues.bytecode]] to help translate
   * the individual `Byte`s into a correponding [[ByteCode]].
   *
   * @param bc  a vector of bytes representing bytecodes
   * @return    a vector of `ByteCode` objects
   */
  def parse(bc: Vector[Byte]): Vector[ByteCode] = {
    val f = new MyByteCodeFactory
    val flip = bytecode.map(_.swap)
    var out = Vector[ByteCode]()
    val iter = bc.iterator
    while(iter.hasNext){ //iterate thru bc
      val s = iter.next()
      val n: ByteCode = { //special case if iconst, if not just do it normally
        if(flip(s).equals("iconst")) f.make(s, iter.next().toInt)
        else f.make(s)
      }
      out = out :+ n
    }
    return out
  }
}
