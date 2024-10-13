package ip.DMA

/*
 use to control the DMA engine interrupt
*/

import spinal.core._
import spinal.lib._

case class InterruptBundle() extends Bundle with IMasterSlave {
  val readDone = Bool()
  val writeDone = Bool()
  override def asMaster(): Unit = {
    out(readDone, writeDone)
  }
}


class InterruptContrl extends Component{

  val io = new Bundle{
    val irq = master(InterruptBundle())
    val readBusy = in Bool()
    val writeBusy = in Bool()
  }
  


}
