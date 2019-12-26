package fintech.lecture11
import fintech.lecture11.Applicative.ApplicativeOps

import scala.io.StdIn

object WriterApplicative {
  implicit def writerApplicativeInst[M]: Applicative[Writer[M, *]] = 
    new Applicative[Writer[M, *]] {
      def ap[A, B](f: Writer[M, A => B])(fa: Writer[M, A]): Writer[M, B] = Writer(
        f.log ++ fa.log, f.value(fa.value)
      )

      def pure[A](a:A) = Writer(List.empty[M], a)
    }

  implicit def toApplicativeOps[M,A,B](w: Writer[M, A => B]) = 
    new ApplicativeOps[Writer[M, *], A, B](w)
}

import org.scalacheck._

object WriterApplicativeLawsSpec extends Properties("ApplicativeLaws") with ApplicativeLaws[Writer[Long, *]] {
  import Prop.forAll
  import Gen._
  import WriterApplicative._

  implicit val genSmallInteger: Arbitrary[Int] = Arbitrary(Gen.choose(0, 10000))
  implicit val genString: Arbitrary[String] = Arbitrary(Gen.alphaLowerStr)

  override implicit def F = writerApplicativeInst[Long] 

  // ap(x)(pure(identity)) == x
  property("idenity") = forAll { (value: String, log: List[Long]) =>
    identityLaw(Writer(log, value))
  }

  // pure(g(x)) == ap(pure(x))(pure(g))
  property("composition") = forAll { value: Int =>
    compositionLaw({x: Int => (x * 2).toString()}, value)
  }

  // ap(pure(y))(x) == ap(x)(pure(g => g(y)))
  property("order") = forAll { (value: Int, log: List[Long]) =>
    val x = Writer(log, {x: Int => (x * 3).toString()})
    orderLaw(x, value)
  }

  // ap(ap(x)(y))(z) == ap(x)(ap(y)(ap(z)(pure(combo))))
  property("associative") = forAll { (value: Int, logX: List[Long], logY: List[Long], logZ: List[Long]) =>
    val fx = Writer(logX, {value: String => value.toDouble})
    val fy = Writer(logY, {value: Int => value.toString})
    val z = Writer(logZ, value)
    
    associativeLaw(fx, fy, z)
  }
}




object WriterApp2 {
  import WriterApplicative._

  def input(): Writer[Long,String] = {
    val startTime = System.currentTimeMillis()
    val line = StdIn.readLine()
    Writer.single(line, System.currentTimeMillis() - startTime)
  }

  val in1 = input()
  val in2 = input()

  println(
    Applicative[Writer[Long,*]].pure(
      {(x: String, y: String) => x.size + y.size}.curried
    ) <*> in1 <*> in2
  )
}

