package factory

/**
 * Created by tom on 10/23/15.
 */
/**
 * [[bc.ByteCodeValues]] is a trait containing a couple of useful definitions.
 * You should extend this trait in other classes that might find the
 * definitions below useful.
 */
class MyByteCodeValues extends bc.ByteCodeValues{
  /**
   * A vector of the allowable bytecode names.
   */
  //val names = Vector("iconst", "iadd", "isub", "imul", "idiv", "irem",
  //  "ineg", "iinc", "idec", "idup", "iswap", "print")

  /**
   * A map from bytecode names to a unique byte that represents them.
   */
  //val bytecode = names.zip(1.to(names.length).map(_.toByte)).toMap
}