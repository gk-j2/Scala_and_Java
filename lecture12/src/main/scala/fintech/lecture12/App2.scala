package fintech.lecture12

import scala.io.StdIn.readLine
import scala.util.Try

object App2 {

  def parseInt(s: String): Option[Int] =
    Try(s.toInt).toOption

  case class IO[A](unsafeRun: () => A) {
    self =>

    def map[B](f: A => B): IO[B] =
      IO(() => f(self.unsafeRun()))

    def flatMap[B](f: A => IO[B]): IO[B] =
      IO(() => f(self.unsafeRun()).unsafeRun())
  }

  object IO {
    def pure[A](a: => A): IO[A] = IO(() => a)
  }

  def putStringLine(line: String): IO[Unit] = IO(() => println(line))

  def getStringLine: IO[String] = IO(() => readLine())

  def nextInt(upper: Int): IO[Int] =
    IO(() => scala.util.Random.nextInt(upper))

  def gameLoop(name: String): IO[Unit] =
    for {
      num <- nextInt(5).map(_ + 1)
      _ <- putStringLine(s"Dear $name, please guess a number from 1 to 5:")
      input <- getStringLine
      _ <-
         parseInt(input).fold(
           putStringLine("You did not enter a number")
         ) { guess =>
           if (guess == num) {
             putStringLine(s"You guessed right, $name!")
           } else {
             putStringLine(s"You guessed wrong, $name! The number was: $num")
           }
         }
      cont <- checkContinue(name)
      _ <- if (cont) gameLoop(name) else IO.pure(())
    } yield ()

  def checkContinue(name: String): IO[Boolean] =
    for {
      _ <- putStringLine(s"Do you want to continue, $name?")
      input <- getStringLine
      result <-
        input match {
          case "y" => IO.pure(true)
          case "n" => IO.pure(false)
          case _ => checkContinue(name)
        }
    } yield result

  def mainIO: IO[Unit] =
    for {
      _ <- putStringLine("What is your name?")
      name <- getStringLine
      _ <- putStringLine(s"Hello, $name, welcome to the game!")
      _ <- gameLoop(name)
    } yield ()

  def main(args: Array[String]): Unit =
    mainIO.unsafeRun()
}
