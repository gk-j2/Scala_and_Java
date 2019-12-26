package fintech.lecture03.examples

object Example27CaseClassApply extends App {

  case class Circle(r: Double)
  case class Rectangle(width: Double, height: Double)

  val circle = Circle(13)
  val rectangle = Rectangle(37, 42)

  println(s"circle.r == ${circle.r}")
  println(s"rectangle.width == ${rectangle.width}")
  println(s"rectangle.height == ${rectangle.height}")
}
