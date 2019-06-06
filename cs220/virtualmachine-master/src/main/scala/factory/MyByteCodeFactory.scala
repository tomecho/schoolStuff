package factory

import bc.{InvalidBytecodeException, ByteCodeValues, ByteCode, ByteCodeFactory}
/**
 * Created by tom on 10/23/15.
 */

class MyByteCodeFactory extends ByteCodeFactory with ByteCodeValues{
  /**
   * Returns a [[ByteCode]].
   *
   * This method creates a new [[ByteCode]] object given the `byte`
   * that corresponds to the bytecode (see [[ByteCodeValues]]. If
   * the bytecode requires arguments then an optional integer
   * argument is provided.
   *
   * This method should throw an [[InvalidBytecodeException]] if the
   * given bytecode value is unknown.
   *
   * @param byte  the byte code of a bytecode
   * @param args  an optional integer argument (depends on bytecode)
   * @return a new bytecode object
   */
  def make(byte: Byte, args: Int*): ByteCode = { //just map the corresponding bytecode to its specific class
    if(args.length > 1) throw new InvalidBytecodeException("too long")
    val flip = bytecode.map(_.swap) //my reverse map
    if(!flip.contains(byte)) throw new InvalidBytecodeException("") //doesnt even exist
    val name = flip.get(byte).get
    if(name == "iconst") {
      if(args.length < 1) throw new InvalidBytecodeException("no parametar on iconst") //neeed a param here
      return new iconst(args(0))
    }
    else if(name.equals("iadd")) return new iadd
    else if(name.equals("isub")) return new isub
    else if(name.equals("imul")) return new imul
    else if(name.equals("idiv")) return new idiv
    else if(name.equals("irem")) return new irem
    else if(name.equals("ineg")) return new ineg
    else if(name.equals("iinc")) return new iinc
    else if(name.equals("idec")) return new idec
    else if(name.equals("idup")) return new idup
    else if(name.equals("iswap")) return new iswap
    else if(name.equals("print")) return new print
    throw new InvalidBytecodeException("couldnt find it") //command doesnt exist
  }
}
