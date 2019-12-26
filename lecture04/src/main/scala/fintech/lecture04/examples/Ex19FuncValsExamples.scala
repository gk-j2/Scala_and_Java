package fintech.lecture04.examples

import scala.util.{Failure, Success, Try}

object Ex19FuncValsExamples extends App {
  //MAP api
  Map("foo" -> "bar").map({(kv: Tuple2[String, String]) =>
    kv._1 -> kv._2.length

  })
  Map("foo" -> "bar").map {case (k, v) => k -> v.length}


  //LIST api
  List(1,2,3).flatMap({(x: Int) =>
    List(x, x * 22)
  })
  List(1,2,3).flatMap {x => List(x, x * 22)}


  // TRY and funs
  Try {
    1 / 0
  }.map({(x: Int) =>
    x * 2
  })

  Try {
    1 / 0
  }.map(_ * 2)


  //LOAN pattern
  trait RES {
    def commit(): Unit
    def rollback() : Unit
  }

  def withTx[A](res: => RES)(f: RES => A): () => Try[A] = {() =>
    val _res = res
    Try { f(_res) }.transform (
      {a => _res.commit(); Success(a) },
      {t => _res.rollback(); Failure(t) }
    )
  }

}
