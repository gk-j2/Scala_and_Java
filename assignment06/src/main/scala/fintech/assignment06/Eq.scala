package fintech.assignment06

/**
  * Реализуйте инстансы для typeclass Eq в объекте EqInstances:
  *  -- для примитивных типов (обязательно должна быть реализация для boolean, int, long)
  *  -- для класса exercises.Rational
  *  -- для классов для всех возможных их комбинаций в Option, List, Map
  *
  * Определите синтаксис для typeclass Eq в объекте EqSyntax.
  *
  * После определения всех инстансов и синтаксиса должны выполняться следующие проверки:
  *
  * import EqInstances._
  * import EqSyntax._
  *
  * 1 eqv 1 // возвращает true
  * 1 === 2 // возвращает false
  * 1 !== 2 // возвращает true
  * 1 === "some-string" // не компилируется
  * 1 !== Some(2) // не компилируется
  * List(true) === List(true) // возвращает true
  */
trait Eq[A] {
  def eqv(a: A, b: A): Boolean
}

object EqInstances {
  //--------------------------------------------------------------------
  implicit val intEq: Eq[Int] = new Eq[Int] {
    def eqv(a: Int, b: Int): Boolean = a == b
  }

  implicit val stringEq: Eq[String] = new Eq[String] {
    def eqv(a: String, b: String): Boolean = a == b
  }

  implicit val booleanEq: Eq[Boolean] = new Eq[Boolean] {
    def eqv(a: Boolean, b: Boolean): Boolean = a == b
  }

  implicit val longEq: Eq[Long] = new Eq[Long] {
    def eqv(a: Long, b: Long): Boolean = (a - b) <= 0.0000000000001
  }

  implicit val rationalEq: Eq[Rational] = new Eq[Rational] {
    def eqv(a: Rational, b: Rational):Boolean = (a.numerator.toDouble/b.numerator.toDouble == a.denumerator.toDouble/b.denumerator.toDouble)
  }
//-------------------------------------------------------------------------
  implicit val listBoolEq: Eq[List[Boolean]] = new Eq[List[Boolean]] {
    def eqv(a: List[Boolean], b: List[Boolean]): Boolean = a == b
  }

  implicit val listIntEq: Eq[List[Int]] = new Eq[List[Int]] {
    def eqv(a: List[Int], b: List[Int]): Boolean = a == b
  }

  implicit val listlongEq: Eq[List[Long]] = new Eq[List[Long]] {
    def eqv(a: List[Long], b: List[Long]): Boolean = a == b
  }

  implicit val listRationalEq: Eq[List[Rational]] = new Eq[List[Rational]] {
    def eqv(a: List[Rational], b: List[Rational]): Boolean = a == b
  }

  //-------------------------------------------------------------------------

  implicit val optionBoolEq: Eq[Option[Boolean]] = new Eq[Option[Boolean]] {
    def eqv(a: Option[Boolean], b: Option[Boolean]): Boolean = a == b
  }

  implicit val optionIntEq: Eq[Option[Int]] = new Eq[Option[Int]] {
    def eqv(a: Option[Int], b: Option[Int]): Boolean = a == b
  }

  implicit val optionlongEq: Eq[Option[Long]] = new Eq[Option[Long]] {
    def eqv(a: Option[Long], b: Option[Long]): Boolean = a == b
  }

  implicit val optionRaitonalEq: Eq[Option[Rational]] = new Eq[Option[Rational]] {
    def eqv(a: Option[Rational], b: Option[Rational]): Boolean = a == b
  }

  //---------------------------------------------------------------------------
}

object EqSyntax {
  implicit class EqOps[A](val a: A) extends AnyVal {
    def eqv(b: A)(implicit ev: Eq[A]) = ev.eqv(a, b)
    def ===(b: A)(implicit ev: Eq[A]) = eqv(b)
    def !==(b: A)(implicit ev: Eq[A]) = !eqv(b)
  }
}

object Tests extends App {
  import EqInstances._
  import EqSyntax._

  println(1 eqv 1) // возвращает true
  println(1 === 2) // возвращает false
  println(1 !== 2) // возвращает true
  //1 === "some-string" // не компилируется
  //1 !== Some(2) // не компилируется
  println(List(true) === List(true)) // возвращает true
}
