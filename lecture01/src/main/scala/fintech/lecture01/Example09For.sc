val numbers = List(1, 2, 3)

for (
  n1 <- numbers;
  n2 <- numbers
) println(n1 * n2)

val squares =
  for (n <- numbers) yield n * n

val otherNumbers = List(4, 5, 6)

val complexStuff = for {
  low <- numbers
  high <- otherNumbers
} yield low * high
