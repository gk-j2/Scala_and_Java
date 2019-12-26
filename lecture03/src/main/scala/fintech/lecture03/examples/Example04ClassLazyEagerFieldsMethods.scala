package fintech.lecture03.examples

object Example04ClassLazyEagerFieldsMethods extends App {
  class MyClass(x: Int, val y: Int) {
    val eagerXY = {
      println("eagerXY init")
      x * y
    }
    lazy val lazyXY = {
      println("lazyXY init")
      x * y
    }


    def calcXY = x * y * eagerXY
    def calcXYZ(z: Int) = x * y * z * lazyXY
  }

  val mc = new MyClass(1, 2)
  println("y value : " + mc.y)
  println(mc.calcXY)
  println(mc.calcXYZ(22))
}
