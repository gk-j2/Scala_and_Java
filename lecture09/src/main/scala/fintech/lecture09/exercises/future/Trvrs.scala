package fintech.lecture09.exercises.future
import scala.concurrent.{ExecutionContext, Future}

/**
 * реализуйте foldLeftF для future
 *
 * используйте foldLeftF для реализации метода trvrs
 *
 * возможно придется добавить неявные аргументы
 */
object Trvrs {
  def foldLeftF[T,A](fs: List[Future[T]], zero: A)(fold: (A, T) => A): Future[A] = ???
  def trvrs[T,A](fs: List[Future[T]])(recover: Throwable => T): Future[List[T]] = ???
}