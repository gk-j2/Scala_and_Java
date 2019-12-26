package fintech.lecture11

trait Alternative[F[_]] {
  /***
    * Laws
    * empty <|> x = x
    * x <|> empty = x
    * x <|> (y <|> z) = (x <|> y) <|> z
    */

  def empty[A]: F[A]
  def <|>[A](a: F[A], b: F[A]): F[A]
}

object Alternative {
  def apply[F[_]](implicit alt: Alternative[F]) = alt

  implicit class AlternativeOps[F[_], A](private val fa: F[A]) extends AnyVal {
    def <|>(fb: F[A])(implicit alt: Alternative[F]): F[A] = alt.<|>(fa, fb)
  }
}