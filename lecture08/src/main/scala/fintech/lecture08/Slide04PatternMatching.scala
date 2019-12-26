package fintech.lecture08

object Slide04Example1 extends App {

  def yesOrNo(choice: Int): String =
    choice match {
      case 1 => "yes"
      case 0 => "no"
    }

  println(yesOrNo(1))

  println(yesOrNo(0))

  yesOrNo(2)
}

object Slide04Example2 extends App {

  def yesOrNo(choice: Int): String =
    choice match {
      case 1 => "yes"
      case 0 => "no"
      case _ => "error"
    }

  println(yesOrNo(-1))
}

object Slide04Example3 extends App {

  def yesOrNo(choice: Int): String =
    choice match {
      case 1 | 2 | 3 => "yes"
      case 0 => "no"
      case _ => "error"
    }

  println(yesOrNo(1))
  println(yesOrNo(2))
  println(yesOrNo(3))
}

object Slide04Example4 extends App {

  def yesOrNo(choice: Int): String =
    choice match {
      case 1 => "yes"
      case 0 => "no"
      case unknown => s"Unexpected value: $unknown"
    }

  println(yesOrNo(-1))
}

object Slide04Example5 extends App {

  def check(any: Any): String =
    any match {
      case i: Int => s"Square ${i * i}"
      case s: String => s"Length ${s.length}"
      case unexpected => "Unknown type $unexpected"
    }

  println(check(1))
  println(check("Alice"))
  println(check(true))
}

object Slide04Example6 extends App {

  def check(any: Any): String =
    any match {
      case i: Int if i % 2 == 0 => "Even integer"
      case i: Int if i % 2 == 1 => "Odd integer"
      case s: String => s"Length ${s.length}"
      case unexpected => s"Unknown type $unexpected"
    }

  println(check(1))
  println(check(2))
  println(check(3))
}

object Slide04Example7 extends App {

  val unknown = 123

  def check(value: Int): String =
    value match {
      case `unknown` => "It is 123 actually"
      case unknown => s"Unknown value $unknown"
    }

  println(check(123))
  println(check(1))
}

object Slide04Example8 extends App {

  sealed trait Expression
  case class IntExpression(value: Int) extends Expression
  case class Sum(left: Expression, right: Expression) extends Expression
  case class Mult(left: Expression, right: Expression) extends Expression

  def eval(exp: Expression): Int =
    exp match {
      case IntExpression(i) => i
      case Sum(left, right) => eval(left) + eval(right)
      case Mult(left, right) => eval(left) * eval(right)
    }

  println(eval(Sum(IntExpression(1), Mult(IntExpression(2), IntExpression(3)))))
}

object Slide04Example9 extends App {
  import Slide04Example8._

  def eval(exp: Expression): Int =
    exp match {
      case IntExpression(i) if i >= 0 => i
      case IntExpression(i) if i < 0 => -i
      case Sum(left, right) => eval(left) + eval(right)
      case Mult(left, right) => eval(left) * eval(right)
    }

  println(eval(Sum(IntExpression(-1), Mult(IntExpression(2), IntExpression(-3)))))
}

object Slide04Example10 {
  import Slide04Example8._

  def eval(exp: Expression): Int =
    exp match {
      case IntExpression(i) if i >= 0 => i
      case IntExpression(i) if i < 0 => -i
      case Sum(left, _) => eval(left)
      case Mult(left, _) => eval(left)
    }

  println(eval(Sum(IntExpression(-1), Mult(IntExpression(2), IntExpression(-3)))))
}

object Slide04Example11 {
  import Slide04Example8._

  def eval(exp: Expression): Int =
    exp match {
      case IntExpression(i) => i
      case Sum(IntExpression(i1), IntExpression(i2)) => i1 + i2
      case Sum(left, right) => eval(left) + eval(right)
      case Mult(left, right) => eval(left) * eval(right)
    }

  println(eval(Sum(IntExpression(-1), Sum(IntExpression(2), IntExpression(-3)))))
}

object Slide04Example12 {
  import Slide04Example8._

  def eval(exp: Expression): Int =
    exp match {
      case IntExpression(i) => i
      case Sum(left @ IntExpression(i1), right @ IntExpression(i2)) =>
        println(s"Inside optimized branch. Processing $left and $right")
        i1 + i2
      case Sum(left, right) => eval(left) + eval(right)
      case Mult(left, right) => eval(left) * eval(right)
    }

  println(eval(Sum(IntExpression(-1), Sum(IntExpression(2), IntExpression(-3)))))
}
