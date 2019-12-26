package fintech.lecture06

object Example06ImplicitParameters1 {

  def inttotal(xs: List[Int]): Int = xs.reduce(_ + _)

  def stringtotal(xs: List[String]): String = xs.reduce(_ + _)
}

object Example06ImplicitParameters2 {
  trait Semigroup[A] {
    def combine(a1: A, a2: A): A
  }

  def total[A](xs: List[A])(semigroup: Semigroup[A]): A = xs.reduce(semigroup.combine(_, _))

  val intSemigroup = new Semigroup[Int] {
    override def combine(a1: Int, a2: Int): Int = a1 + a2
  }

  val stringSemigroup = new Semigroup[String] {
    override def combine(a1: String, a2: String): String = a1 + a2
  }

  total(List(1, 2, 3))(intSemigroup)
  total(List("1", "2", "3"))(stringSemigroup)
}

object Example06ImplicitParameters3 {
  trait Semigroup[A] {
    def combine(a1: A, a2: A): A
  }

  def total[A](xs: List[A])(implicit semigroup: Semigroup[A]): A = xs.reduce(semigroup.combine(_, _))

  implicit val intSemigroup = new Semigroup[Int] {
    override def combine(a1: Int, a2: Int): Int = a1 + a2
  }

  implicit val stringSemigroup = new Semigroup[String] {
    override def combine(a1: String, a2: String): String = a1 + a2
  }

  total(List(1, 2, 3))
  total(List("1", "2", "3"))
}
