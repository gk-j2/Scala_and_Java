package fintech.lecture03.examples

object Example29CaseClassCopy extends App {

  case class Rectangle(width: Double, height: Double)

  val rectangle1 = Rectangle(37, 42)
  val rectangle2 = rectangle1.copy(width = 42)

  println(s"rectangle1 == $rectangle1")
  println(s"rectangle2 == $rectangle2")

  class Rectangle1(val width: Double, val height: Double) {
    def copy(width: Double = this.width, height: Double = this.height): Rectangle1 = {
      new Rectangle1(width, height)
    }
  }
}
