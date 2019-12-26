package fintech.lecture09.exercises.future

import scala.concurrent._
import scala.collection.concurrent.TrieMap
import scala.concurrent.duration._
import scala.io.StdIn

/**
 * реализуйте кеширующу надстройку над некой функцией,
 * которая принимает один аргумент и возвращает Future.
 *
 * вычисление должно выполняться только один раз
 * для уникального значения аргумента
 *
 * @param f
 * @tparam I
 * @tparam O
 */
class Cache[I, O](f: I => Future[O]) {
  val storage = TrieMap.empty[I, Future[O]]

  //hint use storage.putIfAbsent and Promise

  def apply(in: I): Future[O] =
}

object CacheTest extends App {
  implicit val ec: ExecutionContext = ExecutionContext.global

  val cache = new Cache[Int, Int]({ x =>
    Future {
      println("starting computing " + x)
      blocking {
        StdIn.readLine()
      }
      x * x
    }
  })

  println(
    Await.result(
      Future.traverse(
        List(1, 1, 2, 2, 3, 3, 3)
      )(cache.apply),
      10 seconds
    )
  )
}