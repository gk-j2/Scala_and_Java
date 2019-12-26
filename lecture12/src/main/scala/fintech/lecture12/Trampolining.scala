package fintech.lecture12

object TrampoliningExample1 

object TrampoliningExample2 {
  sealed trait IO[A] {
    def flatMap[B](f: A => IO[B]): IO[B] = FlatMap(this, f)

    def map[B](f: A => B): IO[B] = flatMap(a => Return(f(a)))
  }

  object IO {
    def pure[A](a: => A): IO[A] = Return(a)

    def delay[A](resume: () => A): IO[A] = Suspend(resume)
  }

  case class Return[A](a: A) extends IO[A]
  case class Suspend[A](resume: () => A) extends IO[A]
  case class FlatMap[A, B](sub: IO[A], callback: A => IO[B]) extends IO[B]
}

object TrampoliningExample3 {
  import TrampoliningExample2._

  @scala.annotation.tailrec
  def run[A](io: IO[A]): A =
    io match {
      case Return(a) =>
        a
      case Suspend(resume) =>
        resume()

      // not safe implementation
      // case FlatMap(sub, callback) =>
      //   run(callback(run(sub))

      case FlatMap(sub, callback) =>
        sub match {
          case Return(a) =>
            run(callback(a))
          case Suspend(resume) =>
            run(callback(resume()))
          case FlatMap(sub2, callback2) =>
            // (sub2.flatMap(callback2)).flatMap(callback)
            //   is equivalent
            // sub2.flatMap(x => callback2(x).flatMap(callback))
            run(sub2.flatMap(x => callback2(x).flatMap(callback)))
        }
    }
}

object TrampoliningExample4 {
  import TrampoliningExample2._
  import TrampoliningExample3._

  def forever[A](action: IO[A]): IO[A] = ???

  def main(args: Array[String]): Unit = {
    run(forever(IO.delay(() => println("Hello world"))))
  }
}
