case class A(a: Option[Int], b: Boolean)

def f(a: A) : Unit = {
  a match {
    case A(Some(i),_) if a % 2 == 0 => println("1")

  }
}