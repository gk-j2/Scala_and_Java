package fintech.exercises05

import fintech.lecture05.Slide02Example4._

/**
 * Напишите тип Maybe[T].
 * Maybe[T] может быть представлен либо классом Just[T], содержащим значение x типа T,
 * либо Missing[T], олицетворяющим отсутствие объекта.
 */
sealed trait Maybe[T] {
  class Just[x: T]
  class Missing[T] {
    Missing.this = None
  }
}

/**
 * Напишите функцию foreach и count для Maybe[T].
 *
 * Напишите функцию oneLevel[T](listOfMaybes: List[Maybe[T]]): Maybe[List[T]]
 */
object Exercise02 {

  def main(args: Array[String]): Unit = {
    // val maybes: List[Maybe[Int]] =
    //   Cons(Just(1), Cons(Missing[Int], Cons(Just(3), Nil[Maybe[Int]]())))

    // println(maybes)
    // println(oneLevel(maybes))
  }
}
