package fintech.lecture11

class FromMonadInstances[F[_]: Monad] {
  val M = implicitly[Monad[F]]
  import Monad._

  implicit def functorInstance: Functor[F] = new Functor[F] {
    def map[A, B](fa: F[A])(f: A => B): F[B] =
      fa >>= f.andThen(M.unit)
  }

  implicit def applicativeInstance: Applicative[F] = new Applicative[F] {
    def ap[A, B](ff: F[A => B])(fa: F[A]): F[B] =
      fa >>= (a => ff >>= (f => M.unit(f(a))))

    def pure[A](a: A) = M.unit(a)
  }
}
