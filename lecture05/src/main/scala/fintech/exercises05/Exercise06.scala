package fintech.exercises05

/**
 * Для интерфейса Shape определите наследников Rectangle и Circle.
 */
sealed trait Shape {
  def square: Double

  def perimeter: Double
}

final case class Rectangl(h: Double, w: Double)

final case class Cyrcle(r: Double)

/**
 * Интерфейс Comparator позволяет сравнивать два объекта типа T.
 * Напишите две реализации этого интерфейса, которые бы сравнивали произвольные
 * объекты типа Shape по периметру и по площади.
 */
trait Comparator[-T] {
  def compare(o1: T, o2: T): Double
}

object Exercise06 extends Comparator[Shape] {

  val bySquareComparator: Comparator[Shape] = {
    ???
  }

  val byPerimeterComparator: Comparator[Shape] = ???

  /**
   * С помощью функции max и определенных ранее comparators, сравните между собой
   * различные фигуры: прямоугольник с прямоугольником, окружность с прямоугольником.
   *
   * Подумайте над тем, почему Comparator[Shape] может сравнивать Rectangle с Rectangle.
   * Попытайтесь удалить признак контринвариантности у Comparator.
   */
  def max[T](shape1: T, shape2: T, cmp: Comparator[T]): T =
    if (cmp.compare(shape1, shape2) > 0) shape2 else shape1

}
