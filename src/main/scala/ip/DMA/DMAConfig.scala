package ip.DMA

class DMAConfig {

}



// all mapped registers for the DMA unit
object Register {
  val Ctrl = 0x00
  val Status = 0x04
  val InterruptMask = 0x08
  val InterruptStatus = 0x0c
  val ReaderStartAddr = 0x10
  val ReaderLineLen = 0x14
  val ReaderLineCnt = 0x18
  val ReaderStride = 0x1c

  val WriterStartAddr = 0x20
  val WriterLineLen = 0x24
  val WriterLineCnt = 0x28
  val WriterStride = 0x2c
  val Version = 0x30
  val Configuration = 0x34
}
