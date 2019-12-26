var age = 13

val returned = while (age < 18) {
  println("Underaged, come " +
    "again next year")
  age = age + 1
  // age += 1
}

println("Adult!")