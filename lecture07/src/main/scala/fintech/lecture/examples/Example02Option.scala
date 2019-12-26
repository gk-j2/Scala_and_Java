package fintech.lecture.examples

// meet dat Option: NOTHING OR VALUE
object Example02Option extends App {
  val optionalValue: Option[Int] = Some(42)

  // don't do that
  optionalValue match {
    case Some(value) => println(s"some $value")
    case None => println("nothing")
  }

  // don't do that
  val valueWithFallbackBad = if (optionalValue.isDefined) optionalValue.get else 0

  // DO THAT WAY !!!

  val transformValue = optionalValue.map(_ * 3)
  val transformValue2 = optionalValue.flatMap(x => Some(x / 3))
  val valueWithFallback = optionalValue.fold(0)(identity)
  val exists = optionalValue.exists(_ == 42)
  val forAll = optionalValue.forall(_ != 42)
  optionalValue.foreach(println)
}
