package fintech.lecture.examples

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

// meet dat Try: EXCEPTION OR VALUE
object Example04Try extends App {
  val tryValue: Try[Int] = Try(42 / StdIn.readInt())

  // don't do that
  tryValue match {
    case Success(value) => println(s"some $value")
    case Failure(exception) => println(s"nothing but $exception")
  }

  // don't do that
  val valueWithFallbackBad = if (tryValue.isSuccess) tryValue.get else 0

  // DO THAT WAY !!!

  val transformValue = tryValue.map(_ * 3)
  val valueWithFallback = tryValue.fold(_ => 0, identity)
  val valueWithRecover = tryValue.recover {case e: Exception => 55}
  tryValue.foreach(println)
}
