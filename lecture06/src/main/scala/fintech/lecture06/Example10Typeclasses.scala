package fintech.lecture06

object Example10Typeclasses1 {

  trait Semigroup[A] {
    def combine(a1: A, a2: A): A
  }

  object SemigroupInstances {
    implicit val intSemigroup = new Semigroup[Int] {
      override def combine(a1: Int, a2: Int): Int = a1 + a2
    }

    implicit val stringSemigroup = new Semigroup[String] {
      override def combine(a1: String, a2: String): String = a1 + a2
    }
  }
}

object Semigroup {
  import Example10Typeclasses1.Semigroup

  def total[A](xs: List[A])(implicit s: Semigroup[A]): A =
    xs.reduceLeft((x1, x2) => s.combine(x1, x2))
}

object Example10Typeclasses2 {

  import Example10Typeclasses1._
  import Example10Typeclasses1.SemigroupInstances._

  Semigroup.total(List(1, 2, 3))
  Semigroup.total(List("a", "b", "c"))
}

object Example10Typeclasses3 {
  import Example10Typeclasses1._

  implicit class SemigroupOps[A](val xs: List[A]) extends AnyVal {
    def total(implicit s: Semigroup[A]): A =
      Semigroup.total(xs)
  }
}

object SemigroupSyntaxExample {
  import Example10Typeclasses1.SemigroupInstances._
  import Example10Typeclasses3._

  List(1, 2, 3).total
  List("a", "b", "c").total
}
