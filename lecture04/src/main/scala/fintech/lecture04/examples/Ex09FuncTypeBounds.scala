package fintech.lecture04.examples

object Ex09FuncTypeBounds extends App {
  trait Foo
  class Bar extends Foo
  class Baz extends Bar

  def function1[T <: Bar](x: T): T = x
  def function2[T >: Bar](x: T): T = x

  //val x11: Foo = bar1(new Foo {}) // won't compile
  val x12: Bar = function1(new Bar)
  val x13: Baz = function1(new Baz)

  val x21: Foo = function2(new Foo {})
  val x22: Bar = function2(new Bar)
//  val x23: Baz = function2(new Baz) // won't compile because of x23: Bar requirement
  val x24: Bar = function2(new Baz) // compile
  println(x12+"--"+x13+"--"+x21+"--"+x22+"--"+"--"+x24)
}
