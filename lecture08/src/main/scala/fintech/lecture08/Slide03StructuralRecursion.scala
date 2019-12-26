package fintech.lecture08

sealed trait B
sealed trait C
sealed trait F

object Slide03Example1 {

  case class A(b: B, c: C) {
    def f: F = ???
  }
}

object Slide03Example2 {

  sealed trait A {
    def f: F
  }
  final case class B() extends A {
    override def f: F = ???
  }
  final case class C() extends A {
    override def f: F = ???
  }
}

object Slide03Example3 {

  case class A(b: B, c: C)

  def f(a: A): F =
    a match {
      case A(b1, c1) => ???
      case A(b2, c2) => ???
      // ...
      case A(bN, cN) => ???
    }
}

object Slide03Example4 {

  sealed trait A
  final case class B() extends A
  final case class C() extends A

  def f(a: A): F =
    a match {
      case B() => ???
      case C() => ???
    }
}



object Slide03Example5 {

  sealed trait Feline
  final case object Lion extends Feline
  final case object Tiger extends Feline
  final case class Cat(favouriteFood: String) extends Feline
}

object Slide03Example6 {

  sealed trait Food
  case object Antelope extends Food
  case object TigerFood extends Food
  final case class CatFood(food: String) extends Food
}

object Slide03Example7 {
  import Slide03Example6._

  sealed trait Feline {
    def dinner: Food
  }
  final case object Lion extends Feline {
    override def dinner: Food = Antelope
  }
  final case object Tiger extends Feline {
    override def dinner: Food = TigerFood
  }
  final case class Cat(favouriteFood: String) extends Feline {
    override def dinner: Food = CatFood(favouriteFood)
  }
}

object Slide03Example8 {
  import Slide03Example5._
  import Slide03Example6._

  object Dinner {
    def dinner(feline: Feline): Food =
      feline match {
        case Lion => Antelope
        case Tiger => TigerFood
        case Cat(food) => CatFood(food)
      }
  }
}
