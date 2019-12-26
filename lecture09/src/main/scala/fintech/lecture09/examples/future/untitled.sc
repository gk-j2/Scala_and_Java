import scala.concurrent.Future

Future{println("1"); 1/0}
Future{println("2"); 1+1}

val ff = for {
  r1 <- Future{println("1"); 1/0}
  r2 <- Future{println("2"); 1+1}
} yield (r1+r2)