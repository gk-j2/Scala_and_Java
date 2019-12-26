package fintech.lecture06

object Example05Usage2b extends App {

  implicit class DoubleOps(val d: Double) extends AnyVal {
    def square = d * d
    def cube = d * d * d
  }

  object ReplacedByScalaCompiler {
    class DoubleOps(val d: Double) extends AnyVal {
      def square = d * d
      def cube = d * d * d
    }

    implicit def DoubleOps(d: Double) = new DoubleOps(d)
  }

  val d = 3d

  println(d.square)
  println(d.cube)

}
