package fintech.exercises06

// Дополнительно oпределите инстансы Show для типов Boolean и Double.
//
// Затем постройте инстанс Show для любого List'а, содержащего элементы,
// для которых также имеется реализация Show.
//
trait Show[A] {
  def show(value: A): String
}

object ShowInstances {

  implicit val intShow = new Show[Int] {
    override def show(value: Int) = value.toString
  }

  implicit val stringShow = new Show[String] {
    override def show(value: String) = value
  }

  implicit val booleanShaw = new Show[Boolean] {
    override def show(value: Boolean) = value.toString
  }

  implicit val doubleShow = new Show[Double] {
    override def show(value: Double) = value.toString
  }
}

object ShowSyntax {

  implicit class ShowOps[A](val a: A) extends AnyVal {
    def show(implicit ev: Show[A]): String = ev.show(a)
  }
}

object Example11Show extends App {
  import ShowInstances._
  import ShowSyntax._

  // must compile
  // println(List(1, 2, 3).show)

  // must compile
  // println(List(true, false, true).show)

  // must not compile
  // pritnln(List(1f, -2f).show)

  // must compile
  // println(List(1d, -1d).show)
}
