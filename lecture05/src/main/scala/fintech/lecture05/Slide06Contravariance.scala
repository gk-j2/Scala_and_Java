package fintech.lecture05

object Slide06Example1 {

  trait Printer[-T] {
    def print(obj: T): Unit
  }
}

object Slide06Example2 extends App {
  import Slide06Example1._

  val anyRefPrinter: Printer[AnyRef] = new Printer[AnyRef] {
    def print(any: AnyRef) = println(any)
  }

  val strPrinter: Printer[String] = anyRefPrinter

  strPrinter.print("Hello, World!")
  anyRefPrinter.print("Hello, World!")
}

object Slide06Example3 {

  trait Function1[-A, +B] {
    def apply(a: A): B
  }
}

object Slide06Example4 {

  class Publication(val title: String)
  class Book(title: String, author: String) extends Publication(title)

  object Library {
    val books: Set[Book] = Set(new Book("Programming in Scala", "Odersky"), new Book("Walden", "Unknown"))

    def printBookList(info: Book => AnyRef) = {
      for (book <- books) {
        println(info(book))
      }
    }
  }

  object Customer extends App {

    def getTitle(p: Publication): String = p.title

    Library.printBookList(getTitle)
  }
}
