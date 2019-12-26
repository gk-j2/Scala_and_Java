package fintech.lecture02

object Slide01Example1 {

//  val value: AnyVal = null
  val ref: AnyRef = null
}

object Slide01Example2 {

  def error(message: String): Nothing =
    throw new RuntimeException(message)

  def divide(x: Int, y: Int): Int =
    if (y != 0) x / y else error("Can't divide by zero")
}
