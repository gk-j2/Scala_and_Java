package fintech.lecture08

object Slide05Example1 {

  class EmailAddress(val name: String, val host: String) {

    override def toString(): String = s"EmailAddress($name, $host)"
  }

  object Email {

    def unapply(str: String): Option[EmailAddress] = {
      val parts = str split "@"
      if (parts.length == 2) Some(new EmailAddress(parts(0), parts(1))) else None
    }
  }
}

object Slide05Example2 extends App {
  import Slide05Example1._

  println(Email.unapply("oleg@tinkoff.ru"))
  println(Email.unapply("oleg"))
}

object Slide05Example3 {
  import Slide05Example1._

  def parseEmail(raw: Any): Unit =
    raw match {
      case Email(address) =>
        println(address)
      case _ =>
        println(s"Not an address: $raw")
    }

  parseEmail("oleg@tinkoff.ru")

  parseEmail("oleg")
}

object Slide05Example4 {
  object Email {
  
    def unapply(str: String): Option[(String, String)] = {
      val parts = str split "@"
      if (parts.length == 2) Some((parts(0), parts(1))) else None
    }
  }

  def parseEmail(raw: Any): Unit =
    raw match {
      case Email(name, host) =>
        println(s"$name@$host")
      case _ =>
        println(s"Not an address: $raw")
    }
}
