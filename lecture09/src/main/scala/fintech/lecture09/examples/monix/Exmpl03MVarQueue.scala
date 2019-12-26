package fintech.lecture09.examples.monix

import cats.effect.ExitCode
import monix.eval.{Task, TaskApp}
import monix.catnap._
import scala.concurrent.duration._

object Exmpl03MVarQueue extends TaskApp {
  override def run(args: List[String]): Task[ExitCode] = {
    sealed trait Msg
    case class Num(value: Int) extends Msg
    case object PoisonPill extends Msg

    def ponger(take: Task[Msg], put: Int => Task[Unit]): Task[Unit] =
      take.flatMap {
        case Num(x) =>
          Task.sleep(1.second) >>
            Task(println(s"got ping $x on ${Thread.currentThread().getName}")) >>
            put(x * 2) >>
            ponger(take, put)
        case PoisonPill =>
          Task(println("ponger done"))
      }

    def pinger(
                rounds: Int,
                outs: List[Msg => Task[Unit]],
                ins: List[Task[Int]]
              ): Task[Unit] = {
      if (rounds == 0)
        Task.sequence(outs.map { out => out(PoisonPill) }) >> Task.unit
      else
        Task.sequence(outs.map { out => out(Num(rounds)) }) >>
          Task.sequence(ins.map { in => in.map(x => println(s"got pong $x ${Thread.currentThread().getName}")) }) >>
          pinger(rounds - 1, outs, ins)
    }

    val pingersCount = 3
    val roundsCount = 10

    def mvars() =
      for {
        channels <- Task.sequence((1 to pingersCount).toList.map { _ =>
          Task.parZip2(MVar.empty[Task, Msg](), MVar.empty[Task, Int]())
        })
        pongers <- Task.sequence(channels.map(channel =>
          ponger(channel._1.take, channel._2.put).start))
        pinger <-
          pinger(roundsCount, channels.map(c => c._1.put(_)), channels.map(_._2.take)).start
        _ <- Task.sequence(pongers.map(_.join))
        _ <- pinger.join
      } yield ExitCode.Success

    def queues() =
      for {
        channels <- Task.sequence((1 to pingersCount).toList.map { _ =>
          Task.parZip2(
            ConcurrentQueue.bounded[Task, Msg](4),
            ConcurrentQueue.bounded[Task, Int](4))
        })
        pongers <- Task.sequence(channels.map(channel =>
          ponger(channel._1.poll, channel._2.offer).start))
        pinger <-
          pinger(roundsCount, channels.map(c => c._1.offer(_)), channels.map(_._2.poll)).start
        _ <- Task.sequence(pongers.map(_.join))
        _ <- pinger.join
      } yield ExitCode.Success

    mvars() >>
      queues()
  }
}
