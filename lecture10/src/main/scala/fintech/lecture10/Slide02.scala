package fintech.lecture10

import cats.effect.Blocker
import cats.effect.ExitCode
import cats.effect.Resource
import cats.syntax.functor._
import doobie.ConnectionIO
import doobie.h2.H2Transactor
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import doobie.util.update.Update0
import monix.eval.Task
import monix.eval.TaskApp
import monix.execution.Scheduler

object Slide02Example1 {

  val mainScheduler = Scheduler.fixedPool("main-scheduler", Runtime.getRuntime.availableProcessors())

  val blockingScheduler = Scheduler.io("blocking-scheduler")
  val blocker = Blocker.liftExecutionContext(blockingScheduler)

  val transactor = H2Transactor.newH2Transactor[Task](
    "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
    "username", "password",
    mainScheduler, blocker
  )

}

trait Slide02Example3 {

  val schedulerResource = Resource.make { Task.delay(
    Scheduler.fixedPool("main-scheduler", Runtime.getRuntime.availableProcessors)
  )} { scheduler => Task.delay(scheduler.shutdown) }

  // schedulerResource use { scheduler =>
  //   Task.delay(scheduler.execute(() => println("hello")))
  // }

  val blockerResource = Resource.make { Task.delay {
    val scheduler = Scheduler.io("blocking-scheduler")
    (scheduler, Blocker.liftExecutionContext(scheduler))
  }} { case (scheduler, _) => Task.delay(scheduler.shutdown) } map (_._2)

  val transactorResource: Resource[Task, Transactor[Task]] = for {
    scheduler <- schedulerResource
    blocker <- blockerResource
    transactor <- H2Transactor.newH2Transactor[Task](
      "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
      "username", "password",
      scheduler, blocker
    )
  } yield transactor

  transactorResource use { transactor =>
    println(transactor)
    // ... some usage of transactor
    Task.unit
  }
}

object Slide02Example4 extends TaskApp with Slide02Example3 {

  override def run(args: List[String]): Task[ExitCode] =
    transactorResource use { transactor =>
      val updateQuery: Update0 = sql"""create table REQUESTS(
        ID bigint auto_increment,
        ROOT varchar(128) not null,
        URL varchar(256) not null
      )""".update

      val connectionIO: ConnectionIO[Int] = updateQuery.run

      connectionIO.transact(transactor)
    } as ExitCode.Success
}

object Slide02Example5 extends TaskApp with Slide02Example3 {

  override def run(args: List[String]): Task[ExitCode] =
    transactorResource use { transactor =>

      val updateQuery1: Update0 = sql"""create table REQUESTS(
        ID bigint auto_increment,
        ROOT varchar(128) not null,
        URL varchar(256) not null
      )""".update

      val updateQuery2 = sql"""create table RESULTS(
        ID bigint,
        URL varchar(256) not null,
        SIZE int not null
      )""".update

      val connectionIO: ConnectionIO[Unit] = for {
        _ <- updateQuery1.run
        _ <- updateQuery2.run
      } yield ()

      connectionIO.transact(transactor)
    } as ExitCode.Success
}

object Slide02Example6 extends TaskApp with Slide02Example3 {

  case class Job(root: String, url: String)

  val job1 = Job("root1", "url1")
  val job2 = Job("root2", "url2")

  override def run(args: List[String]): Task[ExitCode] =
    transactorResource use { transactor =>

      val connectionIO: ConnectionIO[(Long, Long)] = for {
        newId1 <- sql"""insert into REQUESTS(ROOT, URL) values (${job1.root}, ${job1.url})"""
          .update
          .withUniqueGeneratedKeys[Long]("ID")
        newId2 <- sql"""insert into REQUESTS(ROOT, URL) values (${job2.root}, ${job2.url})"""
          .update
          .withUniqueGeneratedKeys[Long]("ID")
      } yield (newId1, newId2)

      connectionIO.transact(transactor)
    } as ExitCode.Success
}

object Slide02Example7 extends TaskApp with Slide02Example3 {

  case class Job(id: Long, root: String, url: String)

  override def run(args: List[String]): Task[ExitCode] =
    transactorResource use { transactor =>
      val select: Query0[(Long, String, String)] = sql"""select ID, ROOT, URL from RESULTS"""
        .query[(Long, String, String)]

      // val select1: Query0 = sql"""select ID, ROOT, URL from RESULTS"""
      //   .query[Job]

      val connectionIO: ConnectionIO[List[(Long, String, String)]] = select.to[List]
      // val connectionIO: ConnectionIO = select.unique
      // val connectionIO: ConnectionIO = select.option
      // val connectionIO: ConnectionIO = select.nel

      connectionIO.transact(transactor)
    } as ExitCode.Success
}
