package fintech.lecture11

trait Monad[F[_]] {
  def unit[A](a: A): F[A]
  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
}

object Monad {
  def apply[F[_]](implicit F: Monad[F]) = F

  implicit class MonadOps[F[_], A](private val fa: F[A]) extends AnyVal {
    def >>=[B](f: A => F[B])(
      implicit F: Monad[F]): F[B] = F.flatMap(fa)(f)

    def flatMap[B](f: A => F[B])(
      implicit F: Monad[F]): F[B] = F.flatMap(fa)(f)
  }
}

trait MonadLaws[F[_]] {
  import Monad._
  implicit def F: Monad[F]
  
  def unitLaw[A, B](a: A, f: A => F[B]) =
    (F.unit(a) >>= f) == f(a)

  def identityLaw[A, B](fa: F[A]) =
    (fa >>= F.unit) == fa

  def associativeLaw[A, B, C](fa: F[A], f: A => F[B], g: B => F[C]) = 
    ((fa >>= f) >>= g) == (fa >>= {x => f(x) >>= g})
}