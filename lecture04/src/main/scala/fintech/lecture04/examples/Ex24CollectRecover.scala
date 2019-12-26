package fintech.lecture04.examples

import scala.util.Try

object Ex24CollectRecover extends App {
  println(List(1,2,3,4, 6).collect{case x if x % 3 == 0 => x * 2})
  println(List(1,"two",3,"four").collect{case x: String => x * 2})

  println(Try {
    0 / 2
  }.recover {
    case x: ArithmeticException => -1
  })
}
