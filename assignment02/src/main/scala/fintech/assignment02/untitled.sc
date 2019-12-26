def arg(r: Double, i: Double): Double = {
  if (r == 0 && i > 0) {val res = math.Pi / 2; res}
  else if (r == 0 && i < 0) {val res = (-1) * math.Pi / 2; res}
  else if (r > 0) {val res = math.atan(i/r); res}
  else if (r < 0 && i > 0) {val res = math.Pi + math.atan(i/r); res}
  else {val res = (-1) * math.Pi + math.atan(i/r); res}
}
  arg(1,1)