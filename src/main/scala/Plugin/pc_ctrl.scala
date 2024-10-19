package LightCore


import spinal.core._
import spinal.lib._
import global._
/*
the pc component control the pc value with branch and jump
*/

class pc_ctrl(var resetVector : BigInt = 0x80000000l) extends Component {
  val io = new Bundle{
    val halt = in Bool()
    val jump = slave Flow UInt(PHYSICAL_WIDTH bits)
    val pc = master Stream UInt(PC_WIDTH bits)
  }
  val pc = Reg(UInt(PC_WIDTH bits)).init(resetVector)
  val increase = RegInit(False)
  val pc_plus = Mux(increase, pc + 4, pc)
  /* priority in the pc management jump > halt > increase */

  pc := Mux(io.halt, pc, pc_plus)
  when(io.jump.valid){
    pc := io.jump.payload
  }

  increase := io.pc.fire && !io.halt
  io.pc.payload := pc
  io.pc.valid := !io.halt
}
