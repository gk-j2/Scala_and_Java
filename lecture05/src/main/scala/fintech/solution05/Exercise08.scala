package fintech.solution05

sealed trait Maybe2[+T] {

  def foreach(f: T => Unit): Unit

  def count: Int

  def getOrElse[T2 >: T](other: => T2): T2

  def orElse[T2 >: T](maybe: Maybe2[T2]): Maybe2[T2]
}

case class Just2[+T](t: T) extends Maybe2[T] {

  override def foreach(f: T => Unit): Unit = f(t)

  override def count: Int = 1

  override def getOrElse[T2 >: T](other: => T2): T2 = t

  override def orElse[T2 >: T](maybe: Maybe2[T2]): Maybe2[T2] = this
}

case object Missing2 extends Maybe2[Nothing] {

  override def foreach(f: Nothing => Unit): Unit = ()

  override def count: Int = 0

  override def getOrElse[T2](other: => T2): T2 = other

  override def orElse[T2](maybe: Maybe2[T2]): Maybe2[T2] = maybe
}
