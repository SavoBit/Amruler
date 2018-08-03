package amruler.api

object Rules {
  def apply[T, R](items: Rule[T, R]*): Rules[T, R] = new Rules[T, R](items.toList)

  def apply[T, R](items: List[Rule[T, R]]): Rules[T, R] = apply(items: _*)
}

class Rules[T, R](val rules: List[Rule[T, R]]) extends Iterable[Rule[T, R]] {

  def ++(newRules: List[Rule[T, R]]) = Rules(rules ++ newRules)

  def ++(newRules: Rule[T, R]*) = Rules(rules ++ newRules:_*)

  def stream: Stream[Rule[T, R]] = rules.toStream

  override def iterator: Iterator[Rule[T, R]] = rules.iterator
}
