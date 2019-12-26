package fintech.solution05

trait Iterator[F[_]] {

  def foreach[A](fa: F[A], f: A => Unit): Unit
}

object Iterator {

  val optionIterator = new Iterator[Option] {
    override def foreach[A](fa: Option[A], f: A => Unit): Unit = fa.foreach(f)
  }

  val listIterator = new Iterator[List] {
    override def foreach[A](fa: List[A], f: A => Unit): Unit = fa.foreach(f)
  }
}

object Exercise10 {

  def printAllShapes[F[_]](shapes: F[Shape])(iterator: Iterator[F]): Unit =
    iterator.foreach(shapes, println _)


  def main(args: Array[String]): Unit = {

    printAllShapes[Option](Some(Circle(2d)))(Iterator.optionIterator)

    printAllShapes[List](List(Circle(2d), Rectangle(3d, 4d)))(Iterator.listIterator)
  }
}
