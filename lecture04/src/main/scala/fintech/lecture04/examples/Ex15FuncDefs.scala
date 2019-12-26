package fintech.lecture04.examples

import scala.math._

object Ex15FuncDefs {
  object F0 {
    val func: (Int, Int) => (Int) = { (x: Int, y: Int) =>
      x + y
    }

    def polyFunc[T](implicit num: Numeric[T]): (T, T) => (T) = { (x: T, y: T) =>
      num.plus(x, y)
    }

    func(1, 2)
    val polyf = polyFunc[Long]
    polyf(2L, 3L)
  }

  object F1 {
    val func = {(x: Int, y: Int) =>
      x + y
    }

    def polyFunc[T](implicit num: Numeric[T]) = { (x: T, y: T) =>
      num.plus(x, y)
    }

    func(1, 2)
    val polyf = polyFunc[Long]
    polyf(2L, 3L)
  }

  object F2 {
    val func: (Int, Int) => (Int) = { (x, y) =>
      x + y
    }

    def polyFunc[T](implicit num: Numeric[T]): (T, T) => (T) = { (x, y) =>
      num.plus(x, y)
    }

    func(1, 2)
    val polyf = polyFunc[Long]
    polyf(2L, 3L)
  }

  object F3 {
    val func: (Int, Int) => (Int) = ( _ + _ )

    def polyFunc[T](implicit num: Numeric[T]): (T, T) => (T) = { num.plus(_, _) }

    func(1, 2)
    val polyf = polyFunc[Long]
    polyf(2L, 3L)
  }

  object F4 {
    val func: (Int, Int) => (Int) =  _ + _

    def polyFunc[T](implicit num: Numeric[T]): (T, T) => (T) = num.plus

    func(1, 2)
    val polyf = polyFunc[Long]
    polyf(2L, 3L)
  }

  object F5 {
    val func = (_: Int) + (_: Int)

    def polyFunc[T](implicit num: Numeric[T]) = num.plus(_: T, _: T)

    func(1, 2)
    val polyf = polyFunc[Long]
    polyf(2L, 3L)
  }


  // case syntax!
  object FCase {
    type FTYpe[T1, T2, T3] = Tuple2[T1, T2] => T3

    def ffff[T1, T2, T3]: FTYpe[T1, T2, T3] = { case (t1, t2) =>
        ???
    }
  }
}
