package fintech.lecture08

object Slide01Example1 {

  case class User(login: String, age: Int) {
    def greetMessage = s"Hello, $login"
  }
}

object Slide01Example2 {
  import Slide01Example1._

  val user: User = User("Alice", 10)

  val login = user.login
  val age = user.age
}

object Slide01Example3 extends App {
  import Slide01Example1._

  val user: User = User("Alice", 10)

  println(user)
}

object Slide01Example4 extends App {
  import Slide01Example1._

  val alice1 = new User("Alice", 20)
  val alice2 = new User("Alice", 20)

  println(alice1 == alice2)
  println(alice1 eq alice2)
}

object Slide01Example5 extends App {
  import Slide01Example1._

  val alice = new User("Alice", 20)

  val bob = alice.copy(login = "Bob")

  println(alice)
  println(bob)
}

object Slide01Example6 {
  import Slide01Example1._

  User("Alice", 20)

  // is equivalent to

  User.apply("Alice", 20)
}

object Slice01Example7 {

  trait Visitor {
    def name: String
  }

  case class User(name: String, age: Int) extends Visitor

  case object Anonymous extends Visitor {
    override def name = "Anonymous"
  }
}
