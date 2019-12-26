package fintech.lecture04.examples

object Ex20CaseApplyUnapply {

  case class Foo(bar: String, baz: Int)
  val fooFactory = Foo.apply _
  val fooUnassemble = Foo.unapply _
  fooFactory("ger", 42)
  fooUnassemble(fooFactory("ger", 42))

  val fixedbar = Foo.apply("fixed", _: Int)
  val fixedbarF = (Foo.apply _)("fixed", _: Int)
  val bar1 = fixedbar(42)
  val bar2 = fixedbarF(41)
  val fixedClones = bar1.copy(_: String, bar1.baz)
  val fixedClonesF = (bar2.copy _)(_: String, bar1.baz)
  fixedClones("asd")
  fixedClonesF("qwe")

}
