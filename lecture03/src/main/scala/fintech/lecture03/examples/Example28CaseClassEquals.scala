package fintech.lecture03.examples

object Example28CaseClassEquals extends App {

  case class Circle(r: Double)
  case class Rectangle(width: Double, height: Double)

  val circle1 = new Circle(13)
  val circle2 = new Circle(13)

  println(s"(circle1 == circle2) == ${circle1 == circle2}")
}
