package fintech.lecture11


trait Applicative[F[_]] {
  /**
   * кладем a в эффект F
   **/
  def pure[A](a: A): F[A]
  /**
   * применяем функцию f в эффекте F к аргументу a в эффекте F
   **/
  def ap[A, B](f: F[A => B])(fa: F[A]): F[B]
}

/**
 * 
 * (A => B)(F[A]) => F[B]
 * через
 * ap(pure(A => B))(F[A])
 * 
 * ((A, B) => C)(F[A])(F[B]) => F[C] 
 * через
 * ap(ap(pure(((A, B) => C).curried)(F[A]))(F[B]))
 * 
 **/



object Applicative {
  def apply[F[_]](implicit F: Applicative[F]) = F

  implicit class ApplicativeOps[F[_], A, B](private val fa: F[A => B]) extends AnyVal {
    def ap(f: F[A])(implicit F: Applicative[F]): F[B] = F.ap(fa)(f)
    
    def <*>(f: F[A])(implicit F: Applicative[F]): F[B] = F.ap(fa)(f)
  }
}

trait ApplicativeLaws[F[_]] {
  import Applicative._

  implicit def F: Applicative[F]

  def identityLaw[A](fa: F[A]) = (F.pure((a: A) => a)).ap(fa) == fa

  def compositionLaw[A, B](f: A => B, a: A) = F.pure(f(a)) == F.pure(f) <*> F.pure(a)

  def orderLaw[A, B](x: F[A => B], y: A) = { 
    val g = ((g: (A => B)) => g(y))
    x.ap(F.pure(y)) == F.pure(g).ap(x)
  }

  def associativeLaw[A, B, C](x: F[B => C], y: F[A => B], z: F[A]) = 
    x <*> (y <*> z) == 
      (F.pure ({(f1: B => C, f2: A => B) => f1 compose f2 }.curried) <*> x <*> y) <*> z
}