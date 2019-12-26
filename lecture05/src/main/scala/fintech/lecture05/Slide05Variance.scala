package fintech.lecture05

object Slide05Example1 {

  trait List[A] {
    def prepend(a: A): List[A]
  }

  case class Nil[A]() extends List[A] {
    override def prepend(a: A) = Cons(a, Nil())
  }

  case class Cons[A](head: A, tail: List[A]) extends List[A] {
    override def prepend(a: A) = Cons(a, this)
  }
}

object Slide05Example2 {
  import Slide05Example1._

  class SpecializedIntList(head: Int, tail: SpecializedIntList) extends Cons[Int](head, tail) {
    override def prepend(a: Int) = {
      println(a * a)
      Cons(a, this)
    }
  }
}

object Slide05Example3 {
  import Slide05Example2._

  // val xs: List[Any] = new SpecializedIntList(123, ???)
  // xs.prepend("str")
}

object Slide05Example4 {

  class Box[A](var value: A)

  class EquilvalentBox[A](initValue: A) {
    private var _value: A = initValue

    def value: A = _value

    def value_=(value: A): Unit = this._value = value
  }

}
