package LightCore

/**
 * Define global variables for the pipeline
 */

import spinal.core._
import spinal.lib.misc.pipeline._

object global extends AreaRoot{

  val PC_WIDTH = 32
  val PHYSICAL_WIDTH = 32
  val INSTRUCTION_WIDTH = 32
  val REG_NUM = 32
  val REG_NUM_WIDTH = log2Up(REG_NUM)
  val REG_DATA_WIDTH = 32

  require(PC_WIDTH == PHYSICAL_WIDTH, "no virtual address support !!!")

  val PC = Payload(UInt(PC_WIDTH bits))
  val INSTRUCTION = Payload(Bits(INSTRUCTION_WIDTH bits))

}
