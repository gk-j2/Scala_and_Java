package fintech.lecture09.examples.monix

import monix.eval.Task
import monix.execution.Scheduler.Implicits.global
import monix.execution.{Callback, Cancelable, Scheduler}

import scala.util.Try
import concurrent.duration._
import scala.io.StdIn

object Exmpl02Async extends App {
  val printThread = Task(println(s"Running on thread: ${Thread.currentThread.getName}"))

  def defaultScheduler = {
    println("\n\n default scheduler \n\n")

    println("main")
    printThread.runToFuture.foreach(_ => println("done"))

    println("main -> execution scheduler")
    (
      printThread >>
        printThread.executeAsync
    )
      .runToFuture.foreach(_ => println("done"))
  }

  def anotherScheduler = {
    println("\n\n another scheduler \n\n")

    lazy val io = Scheduler.io(name="my-io")

    val printThread = Task(println(s"Running on thread: ${Thread.currentThread.getName}"))

    println("main")
    printThread.runToFuture.foreach(_ => println("done"))

    val forked = printThread.executeOn(io)

    println("main -> io -> main")
    (
      printThread.executeAsync >> // executes on default
      forked >>                   // executes on io
      printThread                 // jump back
    )
      .runToFuture.foreach(_ => println("done"))
  }

  //async API with cancellation
  def callback = {
    def evalDelayed[A](delay: FiniteDuration)(f: => A): Task[A] = {
      Task.create { (scheduler: Scheduler, callback: Callback[Throwable, A]) =>
        val cancelable: Cancelable = scheduler.scheduleOnce(delay) {
          callback(Try(f))
        }
        cancelable
      }
    }

    Task.race(
      evalDelayed(1 seconds) { println("one second") },
      evalDelayed(5 seconds) { println("ten seconds") }
    ).runToFuture
  }

  def fiber = {
    val after = Task(println("after delay")).delayExecution(1 seconds)
    val task = Task(println("i am task")) >> after

    val res = for {
      f <- task.start
      _ <- f.cancel
    } yield println("running")

    res.runToFuture
  }

//  defaultScheduler
//  anotherScheduler
//  callback
//  fiber
  StdIn.readLine()
}