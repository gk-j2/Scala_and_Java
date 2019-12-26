package fintech.lecture09.examples.future

import fintech.lecture09.examples.future.Exmpl05ErrorHandling.f04

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

object Exmpl06FutureObj extends App {
  implicit val ec: ExecutionContext = ExecutionContext.global

  // sequence !
  // List[A] + A => B == Future[List[B]]
  val x1: Future[List[String]] = Future.traverse(
    List(1, 2, 3)
  )(x => Future(s"$x$x$x"))

  // parallel !
  // List[Future[A]] => Future[List[A]]
  val x2: Future[List[Int]] = Future.sequence(
    List(Future(1), Future(2), Future(3))
  )

  def sleepPrintReturn(): Int = {
    val rnd = scala.util.Random.nextInt(500)
    println(s"completed $rnd")
    Thread.sleep(rnd)
    rnd
  }

  println("winner is " +
    Await.result(
      Future.firstCompletedOf(
        List(
          Future {
            sleepPrintReturn()
          },
          Future {
            sleepPrintReturn()
          },
          Future {
            sleepPrintReturn()
          }
        )
      ), 10.seconds)
  )
}