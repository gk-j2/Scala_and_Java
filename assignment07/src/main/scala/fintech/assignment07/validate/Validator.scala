package fintech.assignment07.validate

import scala.language.implicitConversions

trait Validator[+E, T] {
  def validate(in: T): Validated[E, T]
}

object Validator {
  /**
   * комбинирует несколько валидаторов вместе
   * @param vs
   * @tparam E
   * @tparam T
   * @return
   */
  def combine[E, T](vs: Validator[E, T]*): Validator[E, T] = ???

  /**
   * создает валидатор для типа T1 из валидатора для типа T2 и функции, преобразующей T1 => T2
   * @param v
   * @param f
   * @tparam E
   * @tparam T1
   * @tparam T2
   * @return
   */
  def contramap[E, T1, T2](v: Validator[E, T2], f: T1 => T2): Validator[E, T1] = ???

  /**
   * Создает range валидатор. В случае ошбки вызывает функцию onError передав туда label и неверное значение.
   * Полученный результат кладется в Invalid.
   * @param min
   * @param max
   * @param onError
   * @param label
   * @tparam E
   * @tparam T
   * @return
   */
  def range[E, T: Ordering](min: T, max: T, onError: (String, T) => E)(label: String): Validator[E, T] = ???

  /**
   * Создает regex валидатор. В случае ошбки вызывает функцию onError передав туда label и неверное значение.
   * Полученный результат кладется в Invalid.
   * @param regex
   * @param onError
   * @param label
   * @tparam E
   * @return
   */
  def regex[E](regex: String, onError: (String, String) => E)(label: String): Validator[E, String] = ???
}


