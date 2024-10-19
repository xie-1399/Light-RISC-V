package LightCore

import spinal.core._
import spinal.lib._

case class decodeParameters(withRVI: Boolean = true,
                            withRVM: Boolean = true,
                            withCsr: Boolean = true,
                            throwIllegal: Boolean = true)

/* the branch type has it's own encoding format*/
object BR extends SpinalEnum {
  val N, NE, EQ, GE, GEU, LT, LTU, J, JR = newElement()
  defaultEncoding = SpinalEnumEncoding("branch")(
    EQ -> 0,
    NE -> 1,
    J -> 2,
    JR -> 3,
    LT -> 4, //less(<)
    GE -> 5, //grater(>=)
    LTU -> 6,
    GEU -> 7,
    N -> 8 // Not a branch
  )
}

object MemoryOp extends SpinalEnum {
  val LOAD, LOAD_U, STORE, NOT = newElement()
  defaultEncoding = SpinalEnumEncoding("memory")(
    NOT -> 0,
    LOAD -> 1,
    LOAD_U -> 2,
    STORE -> 3
  )
}

object Mask extends SpinalEnum {
  val WORD, HALF, BYTE = newElement()
  defaultEncoding = SpinalEnumEncoding("mask")(
    WORD -> 15,
    HALF -> 3,
    BYTE -> 1
  )
}

class decode_unit {

}
