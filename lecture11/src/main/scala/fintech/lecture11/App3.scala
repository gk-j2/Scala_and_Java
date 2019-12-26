package fintech.lecture11

import scala.io.StdIn

object App3 {
  def input(): Writer[Long, String] = {
    val startTime = System.currentTimeMillis()
    val line = StdIn.readLine()
    Writer.single(line, System.currentTimeMillis() - startTime)
  }

  val in1 = input()

  // how to consume more input ???
  println(
    if (in1.value.size < 10) {
      val in2 = input()
      Writer(in1.log ++ in2.log, in2.value)
    } else {
      in1
    }
  )
}
