package fintech.lecture04.exercises.answers

object Exrsz1Ifff extends App {
  def ifff[T, U >: T](condition: Boolean)(lft: =>T)(rgt: =>U): U =
    if (condition) rgt
    else lft

  ifff(1 != 2)(
    throw new Exception("boom")
  )(
    println("42")
  )
}
