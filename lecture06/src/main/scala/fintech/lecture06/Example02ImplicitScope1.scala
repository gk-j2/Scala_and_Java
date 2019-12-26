package fintech.lecture06

object Example02ImplicitScope1 {

  object MyImplicits {
    implicit def intToString(x: Int) = x.toString
  }

  // import MyImplicits.intToString
    /** --- OR --- **/
  // import MyImplicits._


  // val x: String = 123
}
