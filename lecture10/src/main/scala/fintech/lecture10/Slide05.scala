package fintech.lecture10

import monix.eval.Task
import monix.eval.TaskApp
import cats.effect.ExitCode
import scala.io.StdIn
import monix.catnap.MVar

object Slide05Example1 extends TaskApp {

  def readLine: Task[String] =
    Task.delay(StdIn.readLine())

  def printLine(str: String): Task[Unit] =
    Task.delay(println(str))

  override def run(args: List[String]): Task[ExitCode] =
    for {
      mvar <- MVar.empty[Task, Int]()
      _ <- readValue(mvar).start
      _ <- mvar.put(1)
      _ <- printLine("Write #1")
      _ <- mvar.put(2)
      _ <- printLine("Write #2")
    } yield ExitCode.Success

  def readValue(mvar: MVar[Task, Int]): Task[Unit] =
    for {
      _ <- readLine
      value <- mvar.take
      _ <- printLine(s"Value $value is extracted from MVar")
    } yield ()
}
