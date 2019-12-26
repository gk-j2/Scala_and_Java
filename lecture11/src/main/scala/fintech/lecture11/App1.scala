package fintech.lecture11

import scala.io.StdIn

object App1 {
  def input(): Writer[Long,String] = {
    val startTime = System.currentTimeMillis()
    val line = StdIn.readLine()
    Writer.single(line, System.currentTimeMillis() - startTime)
  }

  // how to apply function to value ?
  val w1 = input() match {
    case Writer(log, value) => Writer(log, value * 2)
  }
  println(w1)
}