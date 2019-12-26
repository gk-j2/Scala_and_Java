package fintech.lecture.examples

// meet dat Either : BIASED TUPLE
object Example06Either extends App {
  val eitherValue: Either[String,Int] = Right(42)

  // don't do that
  eitherValue match {
    case Right(value) => println(s"some $value")
    case Left(message) => println(s"nothing but $message")
  }

  // don't do that
  val valueWithFallbackBad = if (eitherValue.isLeft) eitherValue.left.get else 0

  // DO THAT WAY !!!

  val transformValue = eitherValue.map(_ * 3)
  val valueWithFallback = eitherValue.fold(_ => 0, identity)
  eitherValue.foreach(println)
}
