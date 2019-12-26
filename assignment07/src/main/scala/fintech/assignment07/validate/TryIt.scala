package fintech.assignment07.validate

object TryIt extends App {
  import Validator._

  case class Error(label: String, value: String)

  val numV = range[Error, Int](1, 20, {(l, v) => Error(l, v.toString)}) _
  val strV = regex(""".*""", {(l, v) => Error(l, v.toString)}) _

  case class Testee(foo: Int, bar: Int, baz: String)

  val testeeValidator =
    combine(
      contramap(numV("foo field"), {x: Testee => x.foo}),
      contramap(numV("bar field"), {x: Testee => x.bar}),
      contramap(strV("baz field"), {x: Testee => x.baz})
    )

  println(testeeValidator.validate(Testee(4, 10, "lupus est")))
}
