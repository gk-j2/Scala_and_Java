package fintech.assignment05

// Реализовать интерфейс PrefixTree
// Интерфейс позволяет складывать объекты произвольного класса V по заданному "пути" List[K] в дерево
// и изымать их используя комбинацию методов sub и get

// Например, можно на каждом "уровне" дерева хранить Option[V] и Map[K, PrefixTree[K, V]]
trait PrefixTree[K, +V] {

  def get(path: List[K]): Option[V]

  def sub(path: List[K]): PrefixTree[K, V]

  def put[U >: V](path: List[K], value: U): PrefixTree[K, U]

  def size: Int
}

object PrefixTree {

  def apply[K, V] = ???
}
