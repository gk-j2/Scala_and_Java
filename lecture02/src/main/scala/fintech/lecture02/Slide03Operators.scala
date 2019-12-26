package fintech.lecture02

import scala.language.postfixOps

object Slide03Example1 {

  123.toByte
  123.toLong
}

object Slide03Example2 {

  12.+(2).-(3)

  // a.b(c) <--> a b c

  12 + 2 - 3

}

object Slide03Example3 {

  "some string goes here".split(" ")
  "some string goes here" split " "
}

object Slide03Example4 {

  1 + 2L
}

object Slide03Example5 {

  -1.0
  !true
  ~0xFF

  1.0.unary_-
  true.unary_!
  0xFF.unary_~

}

object Slide03Example6 {
  class Square(val s: Int) extends AnyVal {
    def unary_* = s
  }

//  *(new Square(5))
}

object Slide03Example7 {

  123.toByte
  123 toByte
}
