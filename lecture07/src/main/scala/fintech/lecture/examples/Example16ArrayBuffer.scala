package fintech.lecture.examples

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

//array list based buffer
object Example16ArrayBuffer {
  // construct
  val buffer1 = ArrayBuffer("one", "two", "three")
  val buffer2 = ArrayBuffer.tabulate(42)(_ + 1)

  //access
  val elem = buffer1(1)
  val head = buffer1.head
  val headOpt = buffer1.headOption
  val tail = buffer1.tail
  val part = buffer1.take(2)

  //updates in place
  buffer2(5) = 1
  buffer1 ++= buffer1
  // etc
}
