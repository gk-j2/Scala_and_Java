package fintech.exercises05

// Напишите интерфейс Iterator[F[_]]. Iterator должен содержать метод "foreach",
// который позволяет вызывать переданную функцию f на всех елементах внутри F.
// Реализуйте интерфейс для типов Option и List.
//
// Напишите метод printAllShapes[F[_]](shapes: F[Shape], iterator: Iterator[F]): Unit
// Который будет выводить информацию о всех фигурах из еще неизвестной коллекции F


sealed trait Iterator[F[_]] {
  def foreach[A](fa: F[A], f: A=> ()):Unit
}

object iterators {
  val

}