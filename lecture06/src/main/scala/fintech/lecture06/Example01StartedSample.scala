package fintech.lecture06

object Example01StartedSample1 {

  val javaList = new java.util.ArrayList[Int]()
  javaList.add(1)
  javaList.add(2)
  javaList.add(3)

//  javaList.headOption
//    gives error "Unable to resolve symbol headOption"
}

object Example01StartedSample2 extends App {
  import Example01StartedSample1._

  class JavaListOps[A](val xs: java.util.List[A]) extends AnyVal {
    def headOption: Option[A] =
      if (xs.isEmpty)
        None
      else
        Some(xs.get(0))
  }

  implicit def javalistToWrapper[A](xs: java.util.List[A]): JavaListOps[A] = new JavaListOps(xs)

  println(javaList.headOption)
}

object Example01StartedExample3 {

  implicit def value2option[A](value: A): Option[A] = Some(value)

  implicit def value2either[A](value: A): Either[Nothing, A] = Right(value)
}
