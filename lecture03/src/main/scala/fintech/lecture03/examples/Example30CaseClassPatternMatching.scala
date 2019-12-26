package fintech.lecture03.examples

object Example30CaseClassPatternMatching extends App {

  case class Rectangle(width: Double, height: Double)

  val rectangle = Rectangle(37, 42)

  rectangle match {
    case Rectangle(w, h) => println(s"Rectangle has width $w and height $h")
  }
}