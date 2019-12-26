package fintech.lecture12

import scala.io.StdIn.readLine
import scala.language.higherKinds
import scala.util.Try

object App4 {

  def parseInt(s: String): Option[Int] =
    Try(s.toInt).toOption

  trait Monad[F[_]] {
    def pure[A](a: => A): F[A]

    def flatMap[A, B](fa: F[A], func: A => F[B]): F[B]

    def map[A, B](fa: F[A], func: A => B): F[B]
  }

  object Monad {
    def apply[F[_] : Monad]: Monad[F] = implicitly[Monad[F]]

    def pure[F[_]: Monad, A](a: A): F[A] = Monad[F].pure(a)
  }

  implicit class MonadOps[F[_], A](val fa: F[A]) extends AnyVal {
    def map[B](f: A => B)(implicit monad: Monad[F]) = monad.map(fa, f)

    def flatMap[B](f: A => F[B])(implicit monad: Monad[F]) = monad.flatMap(fa, f)
  }



  trait Console[F[_]] {
    def getStringLine: F[String]
    def putStringLine(line: String): F[Unit]
  }
  object Console {
    def apply[F[_]: Console]: Console[F] = implicitly[Console[F]]

    def putStringLine[F[_]: Console](line: String): F[Unit] =
      Console[F].putStringLine(line)

    def getStringLine[F[_]: Console]: F[String] =
      Console[F].getStringLine
  }



  trait Random[F[_]] {
    def nextInt(upper: Int): F[Int]
  }

  object Random {
    def apply[F[_]: Random]: Random[F] = implicitly[Random[F]]

    def nextInt[F[_]: Random](upper: Int): F[Int] =
      Random[F].nextInt(upper)
  }



  case class IO[A](unsafeRun: () => A) {
    self =>

    def map[B](f: A => B): IO[B] =
      IO(() => f(self.unsafeRun()))

    def flatMap[B](f: A => IO[B]): IO[B] =
      IO(() => f(self.unsafeRun()).unsafeRun())
  }

  object IO {
    def pure[A](a: => A): IO[A] = IO(() => a)

    implicit val monadIO = new Monad[IO] {
      override def pure[A](a: => A): IO[A] =
        IO.pure(a)

      override def flatMap[A, B](fa: IO[A], func: A => IO[B]): IO[B] =
        fa.flatMap(func)

      override def map[A, B](fa: IO[A], func: A => B): IO[B] =
        fa.map(func)
    }

    implicit val consoleIO = new Console[IO] {
      override def getStringLine: IO[String] =
        IO(() => readLine())
      override def putStringLine(line: String): IO[Unit] =
        IO(() => println(line))
    }

    implicit val randomIO = new Random[IO] {
      override def nextInt(upper: Int): IO[Int] =
        IO(() => scala.util.Random.nextInt(upper))
    }
  }

  import Monad._
  import Console._
  import Random._

  def gameLoop[F[_]: Monad: Random: Console](name: String): F[Unit] =
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
      _ <- if (cont) gameLoop(name) else pure(())
    } yield ()

  def checkContinue[F[_]: Monad: Console](name: String): F[Boolean] =
    for {
      _ <- putStringLine(s"Do you want to continue, $name?")
      input <- getStringLine
      result <-
        input match {
          case "y" => pure(true)
          case "n" => pure(false)
          case _ => checkContinue(name)
        }
    } yield result

  def main[F[_]: Monad: Random: Console]: F[Unit] =
    for {
      _ <- putStringLine("What is your name?")
      name <- getStringLine
      _ <- putStringLine(s"Hello, $name, welcome to the game!")
      _ <- gameLoop(name)
    } yield ()

  def mainIO: IO[Unit] = main[IO]


  case class TestData(input: List[String], output: List[String], nums: List[Int]) {
    def nextInt: (TestData, Int) =
      (this.copy(nums = nums.tail), nums.head)

    def putStringLine(line: String): (TestData, Unit) =
      (this.copy(output = line :: output), ())

    def getStringLine: (TestData, String) =
      (this.copy(input = input.tail), input.head)
  }

  case class TestIO[A](run: TestData => (TestData, A)) {
    self =>

    def map[B](func: A => B): TestIO[B] =
      TestIO { data =>
        val (newData, a) = self.run(data) 
        (newData, func(a))
      }

    def flatMap[B](func: A => TestIO[B]): TestIO[B] =
      TestIO { data =>
        val (newData, a) = self.run(data)
        func(a).run(newData)
      }
  }

  object TestIO {
    implicit val monadTestIO: Monad[TestIO] = new Monad[TestIO] {
      override def pure[A](a: => A): TestIO[A] =
        TestIO((_, a))

      override def flatMap[A, B](fa: TestIO[A], func: A => TestIO[B]): TestIO[B] =
        fa.flatMap(func)

      override def map[A, B](fa: TestIO[A], func: A => B): TestIO[B] =
        fa.map(func)
    }

    implicit val randomTestIO: Random[TestIO] = new Random[TestIO] {
      override def nextInt(upper: Int): TestIO[Int] =
        TestIO(_.nextInt)
    }

    implicit val consoleTestIO = new Console[TestIO] {
      override def getStringLine: TestIO[String] =
        TestIO(_.getStringLine)

      override def putStringLine(line: String): TestIO[Unit] =
        TestIO(_.putStringLine(line))
    }
  }

  def mainTestIO: TestIO[Unit] = main[TestIO]

  def runTest(data: TestData): List[String] =
    mainTestIO.run(data)._1.output.reverse

  val testData =
    TestData(
      input = "John A De Goes" :: "1" :: "n" :: Nil,
      output = Nil,
      nums = 0 :: Nil
    )

  def main(args: Array[String]): Unit =
    println(runTest(testData).mkString("\n"))
}
