
/*
MIT License

Copyright (c) 2024 xie-1399

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 _      _         _      _    _____
| |    (_)       | |    | |  /  __ \
| |     _   __ _ | |__  | |_ | /  \/  ___   _ __   ___
| |    | | / _` || '_ \ | __|| |     / _ \ | '__| / _ \
| |____| || (_| || | | || |_ | \__/\| (_) || |   |  __/
\_____/|_| \__, ||_| |_| \__| \____/ \___/ |_|    \___|
            __/ |
           |___/
*/

/* build the pipeline in the LightCore */
package LightCore
import spinal.core._
import spinal.lib._
import spinal.lib.misc.pipeline._
import global._

class pipeline() extends Component {

  val io = new Bundle{
    val instruction = slave Stream Bits(INSTRUCTION_WIDTH bits)
    val physicalAddress = master Stream UInt(PHYSICAL_WIDTH bits)
  }

  val builder = new NodesBuilder()

  /* fetch stage */
  val fecth = new builder.Node{
    /* control the pc value */
    val pc_ctrl = new pc_ctrl()
    pc_ctrl.io.pc <> io.physicalAddress

    PC := pc_ctrl.io.pc.payload
    INSTRUCTION := io.instruction.payload

    // Todo
    io.instruction.ready.set()
    pc_ctrl.io.halt.clear()
    pc_ctrl.io.jump.valid.clear()
    pc_ctrl.io.jump.payload.clearAll()
  }
  /* decode stage */
  val decode = new builder.Node{
  }
  /* execute stage */
  val execute = new builder.Node{

  }
  /* memory stage */
  val memory = new builder.Node {

  }
  /* write back stage */
  val writeback = new builder.Node{

  }

  builder.genStagedPipeline()
}