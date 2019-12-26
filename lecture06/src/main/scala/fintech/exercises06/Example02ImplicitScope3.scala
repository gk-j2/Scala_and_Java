package fintech.exercises06

// Добавьте метод printMe для типа Int, который бы печатал слово "hello" столько раз,
// чему равняется целое число, на котором он был вызван. Для отрицательных чисел метод
// не должен печатать ничего.

// 2.printMe()
//
// prints
//
// hello
// hello
object MyReachInt {

  class A(val i: Int) {
    def printMe(n: Int): Unit = {
      (0 until n).foreach(_ => println("hello"))
    }
  }
  implicit def intToMyReachInt(i: Int) = A(i)
}