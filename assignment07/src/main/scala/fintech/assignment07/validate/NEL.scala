package fintech.assignment07.validate

trait NEL[+T] {
  /**
   * выывать функцию f на каждом элементе
   * @param f
   */
  def foreach(f: T => Unit): Unit

  /**
   * приклеивает other к хвосту this
   * @param other
   * @tparam U
   * @return
   */
  def ap[U >: T](other: NEL[U]): NEL[U]
}

object NEL {
  def apply[T](head: T): NEL[T] = ???
}
