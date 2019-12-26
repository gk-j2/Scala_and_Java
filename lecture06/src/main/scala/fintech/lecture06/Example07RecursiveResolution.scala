package fintech.lecture06

object Example07RecursiveResolution1 {

  trait Semigroup[A] {
    def combine(a1: A, a2: A): A
  }

  implicit def mapIntSemigroup[K]: Semigroup[Map[K, Int]] = ???

  implicit def mapStringSemigroup[K]: Semigroup[Map[K, String]] = ???

  implicit def mapDoubleSemigroup[K]: Semigroup[Map[K, Double]] = ???
}

object Example07RecursiveResolution2 {
  import Example07RecursiveResolution1._

  implicit def mapSemigroup[K, V](implicit valuesSemigroup: Semigroup[V]) = new Semigroup[Map[K, V]] {
    override def combine(map1: Map[K, V], map2: Map[K, V]): Map[K, V] =
      map1.foldLeft(map2) { case (acc, (key, value)) =>
        acc.get(key) match {
          case Some(prevValue) => acc + (key -> valuesSemigroup.combine(prevValue, value))
          case None => acc + (key -> value)
        }
      }
  }

  implicit val doubleSemigroup = new Semigroup[Double] {
    override def combine(a1: Double, a2: Double): Double = a1 + a2
  }

  implicitly[Semigroup[Map[String, Double]]]

//  implicitly[Semigroup[Map[String, Double]]]
//
//    is replaced by
//
//  mapSemigroup[String, Int](implicitly[Semigroup[Double]])
//
//    is replaced by
//
//  mapSemigroup[String, Int](doubleSemigroup)
}
