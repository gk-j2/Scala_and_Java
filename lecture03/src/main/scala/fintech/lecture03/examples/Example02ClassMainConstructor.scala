package fintech.lecture03.examples

object Example02ClassMainConstructor extends App {

  class MyClass(x: Int) {
    println(s"$x in da haus")

    val field: Int = x * x
    var mutableField: Int = 0

    def method(y: Int): Int = x * y
  }

}
