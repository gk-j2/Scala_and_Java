package fintech.lecture11
import scala.io.StdIn
import fintech.lecture11.Functor.FunctorOps

object WriterFunctor {
  implicit def writerFunctorInst[M] = 
    new Functor[({type f[x] = Writer[M,x]})#f] {
      def map[A, B](fa: Writer[M,A])(f: A => B): Writer[M,B] = 
        Writer(fa.log, f(fa.value))
    } 

  implicit def toFunctorOps[M,T](w: Writer[M,T]) =
    new FunctorOps[({type f[x] = Writer[M,x]})#f, T](w)    
}

import org.scalacheck._

object WriterFunctorLawsSpec extends Properties("FunctorLaws") with FunctorLaws[Writer[Long,*]] {
  import Prop.forAll
  import Gen._
  import WriterFunctor._

  def F = writerFunctorInst[Long]

  implicit val genSmallInteger: Arbitrary[Int] = Arbitrary(Gen.choose(0, 10000))
  implicit val genString: Arbitrary[String] = Arbitrary(Gen.alphaLowerStr)

  // map(x)(identity) == x
  property("idenity") = forAll { (value: String, log: List[Long]) =>
    identityLaw(Writer(log, value))
  }

  // map(x)(g andThen h) = map(x)(g).map(h)
  property("composition") = forAll { (value: Int, log: List[Long]) =>
    compositionLaw(Writer(log, value), 
      {x: Int => (x * 2).toString()}, 
      {x: String => x.toInt * 3})
  }
}




object WriterApp1 {
  import Functor._
  import WriterFunctor._

  def input(): Writer[Long,String] = {
    val startTime = System.currentTimeMillis()
    val line = StdIn.readLine()
    Writer.single(line, System.currentTimeMillis() - startTime)
  }

  println(input().map {x => x * 2})
}