package fintech.lecture.examples

import scala.collection.mutable.ListBuffer

//linked list based buffer
object Example15ListBuffer {
  // construct
  val buffer1 = ListBuffer("one", "two", "three")

  //access
  val head = buffer1.head
  val headOpt = buffer1.headOption
  val tail = buffer1.tail
  val part = buffer1.take(2)

  //update (in place constant append)
  buffer1 += "four"
  //update (in place constant prepend)
  "zero" +=: buffer1
}