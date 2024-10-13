

/**
 * Define global variables for the pipeline
 */

import spinal.core._
import spinal.lib.misc.pipeline._

object Global extends AreaRoot{

  val PC_WIDTH = 32
  val PHYSICAL_WIDTH = 32
  val INSTRUCTION_WIDTH = 32
  require(PC_WIDTH == PHYSICAL_WIDTH, "PC width parameters error !!!")

  def CPUReset = 0x80000000L
  val PC = Payload(UInt(PC_WIDTH bits))
  val PHYSICAL_ADDRESS = Payload(UInt(PHYSICAL_WIDTH bits))

}
