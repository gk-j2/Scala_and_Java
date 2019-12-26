package fintech.lecture03.examples


object Example30SealedTrait extends App {

  sealed trait Shape
  final case class Circle(r: Double) extends Shape
  final case class Square(x: Double) extends Shape

  val s: Shape = Circle(13)

  s match {
    case Circle(r) => println(s"r = $r")
    case Square(x) => println(s"x = $x")
//     case _ => println("Not a known shape")
    // unnecessary, Shape cannot be extended outside this file
  }
}
