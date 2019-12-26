package fintech.lecture03.examples

object Example26CaseClass extends App {

  case class Circle(r: Double)
  case class Rectangle(width: Double, height: Double)

  val circle = new Circle(13)
  val rectangle = new Rectangle(37, 42)

  println(s"circle.r == ${circle.r}")
  println(s"rectangle.width == ${rectangle.width}")
  println(s"rectangle.height == ${rectangle.height}")
}
