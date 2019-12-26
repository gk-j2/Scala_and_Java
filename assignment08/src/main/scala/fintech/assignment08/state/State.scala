package fintech.assignment08.state

object StatefulRandom {

  import scala.util.Random

  // Если вам необходимо сгенерить рандомное число, то вы можете использовать класс scala.util.Random.
  val rnd = Random

  println(rnd.nextDouble)
  println(rnd.nextInt)
  println(rnd.nextInt)

  // Каждый вызов возвращает новое число. Можно предположить, что rnd имеет какое-то внутреннее
  // состояние. Когда происходит очередной вызов nextInt/nextDouble, то rnd не только возвращает
  // новое случайное число, но изменяет внутреннее состояние.
  //
  // Такое скрытое изменение внутреннего состояние имеет ряд недостатков: программы тяжелее
  // композировать, тестировать, усложняется написание многопоточных программ.
}

object StatelessRandom {

  // Чтобы избавиться от скрытого изменяемого состояния, давайте сделаем его явным. Новая функция
  // nextInt возвращает не только случайное число, но и новое состояние генератора случайных чисел.
  trait Rnd {
    def nextInt: (Int, Rnd)
  }

  // В качесте простой реализации генератора случайных чисел будем использовать следующий класс
  case class SimpleRnd(seed: Long) extends Rnd {
    override def nextInt: (Int, Rnd) = {
      val newSeed = 13 * seed + 41
      val int = (newSeed >>> 3).toInt
      (int, SimpleRnd(newSeed))
    }
  }

  // и простой пример использования
  val rnd = SimpleRnd(12)
  val (firstInt, nextRnd1) = rnd.nextInt
  val (secondInt, nextRnd2) = nextRnd1.nextInt
  val (thirdInt, nextRnd3) = nextRnd2.nextInt
  // Вместо скрытого изменения состояния, мы теперь делаем все изменения явными. Чтобы получать
  // каждый раз разные значения, мы вызываем nextInt на разных объектах: rnd, nextRnd1, nextRnd2 и
  // так далее.

  // Реализуйте функцию, которая будет генерировать пару случайных целых чисел
  def pair(rnd: Rnd): ((Int, Int), Rnd) = ???

  // Функцию, которая генерирует неотрицальные числа
  def nonNegativeInt(rnd: Rnd): (Int, Rnd) = ???

  // Функцию, которая генерирует случайное число от нуля включительно до единицы невключительно
  def double(rnd: Rnd): (Double, Rnd) = ???
}

// Нам удалось избавиться от скрытого изменяемого состояния, но приходится передавать теперь его явно.
// Это приводит к написанию достаточно однообразного кода. Давайте попробуем немного модифицировать
// подход, чтобы избавиться от этого.

object BetterStatelessRandom {
  import StatelessRandom.Rnd

  // Можно заметить, что наши функции pair, nonNegativeInt, double имеют одинаковый шаблон
  // Rnd => (A, Rnd), где тип A зависит от конкретной функции. Можно сказать, что эти функции
  // описывают переход состояния. Давайте попытаемся обобщить этот шаблон в виде класса RandomState.
  case class RandomState[A](run: Rnd => (A, Rnd)) {

    // Прежде всего определите функцию map, которая бы преобразовывала возвращаемое значение.
    def map[B](func: A => B): RandomState[B] = ???

    // Функция flatMap должна позволить комбинировать несколько последовательных RandomState в один.
    def flatMap[B](func: A => RandomState[B]): RandomState[B] = ???
  }

  // Теперь класс RandomState может быть использован внутри for comprehension

  // Функция возвращает постоянное значение при любом состоянии
  def const[A](a: A): RandomState[A] = ???

  // Функция возвращает случайное целое число
  val nextInt: RandomState[Int] = ???

  // Функция возвращает случайное неотрицальное целое число
  val nonNegativeInt: RandomState[Int] = ???

  // Функция возвращает пару случайных неотрицальных целых чисел
  val pair: RandomState[(Int, Int)] = ???

  // Функция возвращает случайное число от нуля до единицы
  val double: RandomState[Double] = ???

  // Функция возвращает список случайной длины из случайных целых чисел
  val randomList: RandomState[List[Int]] = ???

  // Функция должна сконвертировать список из случайных состояний в случайное состояние, которое
  // возвращает список.
  def sequence[A](xs: List[RandomState[A]]): RandomState[List[A]] = ???
}

object Tests extends App {
  import StatelessRandom.SimpleRnd
  import BetterStatelessRandom._

  val rnd = SimpleRnd(123)

  val constRandom = const(1)
  println(constRandom.run(rnd)) // should print 1
  println(constRandom.run(rnd)) // should print 1

  (for {
    i1 <- nonNegativeInt
    i2 <- nonNegativeInt
    i3 <- nonNegativeInt
    i4 <- nonNegativeInt
    i5 <- nonNegativeInt
  } yield {
    println(i1, i2, i3, i4, i5) // should print only positive numbers
  }).run(rnd)

  (for {
    d1 <- double
    d2 <- double
    d3 <- double
    d4 <- double
    d5 <- double
  } yield {
    println(d1, d2, d3, d4, d5) // should print numbers between 0 and 1
  }).run(rnd)

}
