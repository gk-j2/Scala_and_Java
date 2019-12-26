package fintech.solution05

sealed trait Shape {
  def square: Double

  def perimeter: Double
}

case class Rectangle(width: Double, height: Double) extends Shape {

  override def square: Double =
    width * height

  override def perimeter: Double =
    2 * (width + height)
}

case class Circle(radius: Double) extends Shape {

  override def square: Double =
    Math.PI * radius * radius

  override def perimeter: Double =
    2 * Math.PI * radius
}

trait Comparator[-T] {
  def compare(o1: T, o2: T): Double
}

object Exercise06 {

  val bySquareComparator: Comparator[Shape] = (o1: Shape, o2: Shape) => {
    val square1 = o1.square
    val square2 = o2.square
    if (square1 < square2) 1
    else if (square2 > square1) -1
    else 0
  }

  val byPerimeterComparator: Comparator[Shape] = (o1: Shape, o2: Shape) => {
    val perimeter1 = o1.perimeter
    val perimeter2 = o2.perimeter
    if (perimeter1 < perimeter2) 1
    else if (perimeter2 > perimeter1) -1
    else 0
  }

  /**
   * С помощью функции max и определенных ранее comparators, сравните между собой
   * различные фигуры: прямоугольник с прямоугольником, окружность с прямоугольником.
   *
   * Подумайте над тем, почему Comparator[Shape] может сравнивать Rectangle с Rectangle.
   * Попытайтесь удалить признак контринвариантности у Comparator.
   */
  def max[T](shape1: T, shape2: T, cmp: Comparator[T]): T =
    if (cmp.compare(shape1, shape2) > 0) shape2 else shape1

  def main(args: Array[String]): Unit = {
    println(max(Rectangle(1, 2), Rectangle(3, 2), bySquareComparator))

    println(max(Rectangle(1, 2), Circle(5.5), bySquareComparator))
  }
}
