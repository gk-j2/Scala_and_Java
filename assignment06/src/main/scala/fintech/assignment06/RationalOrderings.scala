package fintech.assignment06

/**
  * Для сортировки коллекций в стандартной библиотеке scala используются traits scala.math.Ordering,
  * scala.math.Ordered. Изучите как устроены эти трейты и как они используются для сортировки на примере
  * методов List.sorted, List.sortWith. Постройте реализацию Ordering для сортировки класса Rational:
  * a. по возрастанию
  * б. по убыванию
  * в. по возрастанию абсолютного значения
  * и воспользуйтесь ими для сортировки списков.
  */
object RationalOrderings {

  val naturalAscendingOrdering: Ordering[Rational] = ???

  val naturalDescendingOrdering: Ordering[Rational] = ???

  val absoluteAscendingOrdering: Ordering[Rational] = ???


  def descNaturalSort(xs: List[Rational]): List[Rational] = ???

  def ascAbsoluteSort(xs: List[Rational]): List[Rational] = ???
}
