package LightCore

import spinal.core._
import spinal.lib._
import global._

/*
 Operate the reg file about reading and writing
 update with the Mem sync and write Ports
*/

case class rfRead() extends Bundle with IMasterSlave{
  val rs1Addr = UInt(REG_NUM_WIDTH bits)
  val rs2Addr = UInt(REG_NUM_WIDTH bits)
  val rs1Data = Bits(REG_DATA_WIDTH bits)
  val rs2Data = Bits(REG_DATA_WIDTH bits)
  override def asMaster(): Unit = {
    out(rs1Addr,rs2Addr)
    in(rs1Data,rs2Data)
  }
}

case class rfWrite() extends Bundle with IMasterSlave{
  val address = UInt(REG_NUM_WIDTH bits)
  val enable = Bool()
  val data = Bits(REG_DATA_WIDTH bits)
  override def asMaster(): Unit = {
    out(address, enable, data)
  }
}

class Regfile(Ports:Int = 1) extends Component {
  // jtag and decode stage will send the reg file cmd
  val io = new Bundle {
    /* write the reg from ex */
    val write = slave(rfWrite())
    /* read */
    val read = slave(rfRead())
  }

  val regfile = Mem(Bits(REG_DATA_WIDTH bits), REG_NUM)
  private val registerNames = Seq("zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0_fp", "s1", "a0", "a1", "a2", "a3", "a4",
    "a5", "a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"
  )

  /* debug it */
  val whiteBox = debug.generate {
    new Area {
      for (idx <- 0 until RegNum) {
        val regWire = Bits(RegWidth bits)
        regWire.setName(s"x${idx}")
        regWire := regfile.readAsync(U(idx).resized)
      }
    }
  }

  val write = new Area {
    /* the ex comes first (the order should be carefully ) -> last wins */
    regfile.write(io.write.waddr, io.write.wdata, enable = io.write.we && io.write.waddr =/= 0)
  }

  val read = new Area {
    val rs1 = regfile.readAsync(io.read.Rs1Addr)
    val rs2 = regfile.readAsync(io.read.Rs2Addr)
    val jtagData = regfile.readAsync(io.jtagPort.jtag_Addr)

    io.read.Rs1Data := io.read.Rs1Addr.mux(
      U(0, RegNumLog2 bits) -> B(0, RegWidth bits),
      io.write.waddr -> Mux(io.write.we, io.write.wdata, rs1),
      default -> rs1
    )

    io.read.Rs2Data := io.read.Rs2Addr.mux(
      U(0, RegNumLog2 bits) -> B(0, RegWidth bits),
      io.write.waddr -> Mux(io.write.we, io.write.wdata, rs2),
      default -> rs2
    )
  }
}

