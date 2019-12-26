package fintech.exercises12

import fintech.lecture12.App3.IO

// Реализуйте функцию forever
object TrampoliningExercise {

  /**
    * Создает новое IO, которое бесконечно повторяет исходный action
    */
  def forever[A](action: IO[A]): IO[A] = action.flatMap(_ => forever(action))
  /*for {
    _ <- action
    _ <- if (1 != 2) forever(action)
  } yield ()*/

  def main(args: Array[String]): Unit = {
    forever(IO(() => println("Hello world"))).unsafeRun()
  }
}
