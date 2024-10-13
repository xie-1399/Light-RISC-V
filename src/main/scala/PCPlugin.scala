

import spinal.core._
import spinal.lib._
import Global._

/*
the pc component control the pc value with branch and jump
*/

case class JumpOp() extends Bundle with IMasterSlave{
  val jump = Bool()
  val jumpAddr = UInt(PHYSICAL_WIDTH bits)
  override def asMaster(): Unit = {
    out(jump,jumpAddr)
  }
}


class PCPlugin(var resetVector : BigInt = 0x80000000l) extends Component {

  val io = new Bundle{
    val halt = in Bool()
    val jump = slave(JumpOp())
    val pc = master Stream UInt(PC_WIDTH bits)
  }

  val pc = Reg(UInt(PC_WIDTH bits)).init(CPUReset)

  // priority in the pc management
}
