package fintech.lecture03.examples

object Example04ClassGettersSetters extends App {
  class MyClass(foo: Int, val bar: Double, var baz: String) {
    val barInited = 2 * foo
    var bazInited = baz * 2

    def someFoo = foo * 42
  }

  class Overrides(bar: Int) {
    var _foo = bar

    def foo: Int = _foo * 2
    def foo_=(x: Int) = _foo = x * 2
  }

  val instance = new MyClass(42, 12.0, "hello world")
  println(instance.barInited)
  println(instance.bazInited)
  println(instance.someFoo)
  instance.baz = "241"
  instance.bazInited = "142"


  val overridesInstance = new Overrides(14)
  println(overridesInstance.foo)
  overridesInstance._foo = 12
  overridesInstance.foo = 12
  println(overridesInstance.foo)
}
