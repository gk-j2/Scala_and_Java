package fintech.assignment07.validate

import fintech.assignment07.validate.Validated.???

sealed trait Validated[+E, +A] {
  /**
   * склеивает 2 validated.
   * valid + invalid    == invalid
   * valid + valid      == valid
   * invalid + valid    == invalid
   * invalid + invalid  == invalid (с конкатенацией списка ошибок)
   *
   * @param other
   * @tparam E1
   * @tparam A1
   * @return
   */
  def ap[E1 >: E, A1 >: A](other: Validated[E1, A1]): Validated[E1, A1]

  /**
   * кладет в Validated значение a, если это Valid и ничего не делает
   * если это Invalid
   * @param a
   * @tparam A1
   * @return
   */
  def pure[A1](a: A1): Validated[E, A1]

  /**
   * Превращает Validated в Either
   *
   * @return
   */
  def toEither: Either[List[E], A]
}

object Validated{
  type ??? = Nothing
  /**
   * Создает Valid
   * @param x
   * @tparam E
   * @tparam T
   * @return
   */
  def valid[E,T](x: T): Validated[E, T] = ???

  /**
   * Создает Invalid
   * @param err
   * @tparam E
   * @tparam T
   * @return
   */
  def invalid[E,T](err: E): Validated[E, T] = ???
}

// you should replace "abstract" with right modifier and replace ??? with right type

abstract class Valid[+A](value: A) extends Validated[???,???]

abstract class Invalid[+E](errors: NEL[E]) extends Validated[???,???]
