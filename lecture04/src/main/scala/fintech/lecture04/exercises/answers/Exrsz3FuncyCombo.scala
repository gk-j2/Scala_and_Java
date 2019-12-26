package fintech.lecture04.exercises.answers

object Exrsz3FuncyCombo extends App {
  def combo[A](funcs: List[A => A]): A => A =
    funcs.foldLeft(identity[A] _) {(accF, f) =>
      accF.andThen(f)
    }

  println(combo[Int](List({ _ * 2 }, { _ + 2 }, { _ / 2 }))(5))
}
