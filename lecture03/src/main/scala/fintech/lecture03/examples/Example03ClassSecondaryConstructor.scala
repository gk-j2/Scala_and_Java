package fintech.lecture03.examples

object Example03ClassSecondaryConstructor extends App {
  class MyClass(x: Int, y: Int) {
    var field: Int = x * y

    def this(x: Int) = this(x, x)

    def this(a: Int, b: Int, c: Int) =
      this(a + b + c)

    def this() = {
      this(1)
      this.field = 42
    }

    override def toString: String = s"MyClass(field = $field)"
  }

  println(s"new MyClass(1) == ${new MyClass(1)}")
  println(s"new MyClass(1, 2) == ${new MyClass(1, 2)}")
  println(s"new MyClass(1, 2, 3) == ${new MyClass(1, 2, 3)}")
  println(s"new MyClass() == ${new MyClass()}")

}
