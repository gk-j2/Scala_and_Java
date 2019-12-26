package fintech.lecture08

import scala.util.Random

object Slide09Example1 {

  trait Service {
    def getUser(name: String): User
  }
  
  trait User {
    def getName: String
    def getFollowersCount: Int
  }
}

object Slide09Example2 {
  import Slide09Example1._

  def getFollowersCount(service: Service): Int = {
    val alice = service.getUser("alice")
    val bob = service.getUser("bob")
    val charlie = service.getUser("charlie")
    alice.getFollowersCount + bob.getFollowersCount + charlie.getFollowersCount
  }
}

object Slide09Example3 {
  import Slide09Example1._

  def getFollowersCount(service: Service): Int = {
    val time0 = System.currentTimeMillis
    val alice = service.getUser("alice")
    val time1 = System.currentTimeMillis
    val bob = service.getUser("bob")
    val time2 = System.currentTimeMillis
    val charlie = service.getUser("charlie")
    val time3 = System.currentTimeMillis

    println(s"alice = ${time1 - time0}; bob = ${time2 - time1}; charlie = ${time3 - time2}")

    alice.getFollowersCount + bob.getFollowersCount + charlie.getFollowersCount
  }
}

object Slide09Example4 {

  case class Writer[A](pair: (List[String], A)) {
    def map[B](func: A => B): Writer[B] =
      Writer((pair._1, func(pair._2)))

    def flatMap[B](func: A => Writer[B]): Writer[B] = {
      val (log1, a) = pair
      val (log2, b) = func(a).pair
      Writer((log1 ++ log2, b))
    }
  }
}

object Slide09Example5 {
  import Slide09Example1._
  import Slide09Example4._

  def measure[A](name: String, thunk: => A): Writer[A] = {
    val start = System.currentTimeMillis
    val result = thunk
    val end = System.currentTimeMillis
    Writer(List(s"$name took ${end - start}ms"), result)
  }

  def getFollowersCount(service: Service): Writer[Int] =
    for {
      alice <- measure("alice", service.getUser("alice"))
      bob <- measure("alice", service.getUser("bob"))
      charlie <- measure("alice", service.getUser("charlie"))
    } yield alice.getFollowersCount + bob.getFollowersCount + charlie.getFollowersCount
}

object Slide09Example6 {
  import Slide09Example1._
  import Slide09Example4._

  implicit class ServiceOps(val service: Service) extends AnyVal {

    def getUserW(name: String): Writer[User] = {
      val start = System.currentTimeMillis
      val user = service.getUser(name)
      val end = System.currentTimeMillis
      Writer(List(s"$name took ${end - start}ms"), user)
    }
  }
  
  def getFollowersCount(service: Service): Writer[Int] =
    for {
      alice <- service.getUserW("alice")
      bob <- service.getUserW("bob")
      charlie <- service.getUserW("charlie")
    } yield alice.getFollowersCount + bob.getFollowersCount + charlie.getFollowersCount
}

object Slide09Example7 {
  import Slide09Example1._
  import Slide09Example4._

  implicit class ServiceOps(val service: Service) extends AnyVal {

    def getUserW(name: String): Writer[User] = {
      Writer(Nil, service.getUser(name))
    }
  }
}

object Slide09Example8 extends App {
  import Slide09Example1._
  import Slide09Example4._
  import Slide09Example6._

  case class UserImpl(name: String, followers: Int) extends User {
    def getName: String = name
    def getFollowersCount: Int = followers
  }

  class ServiceImpl extends Service {
    def getUser(name: String): User = {
      Thread.sleep(Random.nextInt(3000))
      new UserImpl(name, Random.nextInt(1000))
    }
  }
  
  val Writer((logs, count)) = getFollowersCount(new ServiceImpl())
  println(s"Result $count")
  println(logs.mkString("\n"))
}
