package fintech.lecture11

import scala.io.StdIn

object App2 {
  def input(): Writer[Long,String] = {
    val startTime = System.currentTimeMillis()
    val line = StdIn.readLine()
    Writer.single(line, System.currentTimeMillis() - startTime)
  }

  val in1 = input()
  val in2 = input()

  // how to apply some f to in1 in2 ???

  val w1 = Writer(in1.log ++ in2.log, in1.value.size + in2.value.size)

  println(w1)
}