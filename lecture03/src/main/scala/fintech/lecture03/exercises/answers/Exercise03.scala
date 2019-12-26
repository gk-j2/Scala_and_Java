package fintech.lecture03.exercises.answers

object Exercise03 {
// смоделируйте дерево арифметического выражения с помощью Val Sum Mul
// реализуйте вычисление подобных выражений
  sealed trait Exp
  final case class Val(x: Int) extends Exp
  final case class Sum(lft: Exp, rgt: Exp) extends Exp
  final case class Mul(lft: Exp, rgt: Exp) extends Exp

  def eval(exp: Exp): Int = exp match {
    case Val(x) => x
    case Sum(lft, rgt) => eval(lft) + eval(rgt)
    case Mul(lft, rgt) => eval(lft) + eval(rgt)
  }
}
