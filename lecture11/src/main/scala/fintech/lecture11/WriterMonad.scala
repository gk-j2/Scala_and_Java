package fintech.lecture11

import scala.io.StdIn
import Monad._

object WriterMonad {
  implicit def writerMonadInst[M] = new Monad[Writer[M, *]] {
    def unit[A](a: A): Writer[M, A] = Writer(List.empty[M], a)

    def flatMap[A, B](fa: Writer[M,A])(
      f: A => Writer[M,B]): Writer[M,B] = f(fa.value) match {
        case Writer(log1, value) => Writer(fa.log ++ log1, value)
      }
  }

  implicit def toMonadOps[M,T](w: Writer[M,T]) = 
    new MonadOps[Writer[M, *], T](w)
}

import org.scalacheck._

object WriterMonadLawsSpec extends Properties("MonadLaws") with MonadLaws[Writer[Long,*]] {
  import Prop.forAll
  import Gen._
  import WriterApplicative._

  implicit def F = WriterMonad.writerMonadInst[Long]

  implicit val genSmallInteger: Arbitrary[Int] = Arbitrary(Gen.choose(0, 10000))
  implicit val genString: Arbitrary[String] = Arbitrary(Gen.alphaLowerStr)

  property("unit") = forAll { (value: Int, log: List[Long]) =>
    unitLaw(value, {x: Int => 
      Writer(log ++ log, (x * 2).toString)
    })
  }
  
  property("idenity") = forAll { (value: String, log: List[Long]) =>
    identityLaw(Writer(log, value))
  }

  property("associative") = forAll { (value: Int, log: List[Long]) =>
    associativeLaw(
      Writer(log, value), 
      {x: Int => 
        Writer(log ++ log, (x * 2.0))
      },
      {x: Double => 
        Writer(log ++ log, (x * 2).toString())
      }
    )
  }
}



object WriterApp3 {
  import WriterFunctor._
  import WriterMonad._

  def input(): Writer[Long,String] = {
    val startTime = System.currentTimeMillis()
    val line = StdIn.readLine()
    Writer.single(line, System.currentTimeMillis() - startTime)
  }

  input() >>= {in1 => 
    if (in1.size < 10) input()
    else Monad[Writer[Long,*]].unit(in1)
  }
}