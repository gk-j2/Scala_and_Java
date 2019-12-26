package fintech.lecture06

trait Show[A] {
  def show(value: A): String
}

object ToStringExample extends App {

  class Group(name: String)

  case class User(name: String, group: Group)

  println(User("John", new Group("root")).toString)
  // prints User(John,fintech.lecture06.ToStringExample$Group@7e0ea639)
}

object ShowInstances {

  implicit val showInt = new Show[Int] {
    def show(value: Int) = value.toString
  }

  implicit val showString = new Show[String] {
    def show(value: String) = value
  }
}

object ShowSyntax {

  implicit class ShowOps[A](val a: A) extends AnyVal {
    def show(implicit ev: Show[A]): String = ev.show(a)
  }
}

object ShowExample extends App {

  import ShowInstances._
  import ShowSyntax._

  class User(val name: String, val age: Int)

  object User {

    implicit val showUser = new Show[User] {
      def show(user: User): String = s"User(name=${user.name.show} age=${user.age.show})"
    }
  }

  import ShowSyntax._
  import User._

  println(new User("John", 30).show)


  class Money(val amount: Double, val currency: String)

  object Money {
    import ShowInstances._
    import ShowSyntax._

    //implicit val showMoney = new Show[Money] {
    //  def show(money: Money): String = s"Money(amount=${money.amount.show} currency=${money.currency.show})"
    //}
  }
}
