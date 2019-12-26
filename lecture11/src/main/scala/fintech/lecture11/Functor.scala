package fintech.lecture11

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {
  def apply[F[_]](implicit F: Functor[F]) = F

  implicit class FunctorOps[F[_], A](
    private val fa: F[A]) extends AnyVal {

    def map[B](f: A => B)(
      implicit F: Functor[F]): F[B] = F.map(fa)(f)

  }
}

trait FunctorLaws[F[_]] {
  import Functor._

  implicit def F: Functor[F]

  def identityLaw[A](fa: F[A]) = fa.map{a: A => a} == fa

  def compositionLaw[A,B,C](fa: F[A], g: A => B, h: B => C) =
    fa.map(g andThen h) == fa.map(g).map(h)
}