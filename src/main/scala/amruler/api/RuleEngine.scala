package amruler.api

trait RuleEngine[T, R] {

  def fire(rules: Rules[T, R], obj: T): List[R]
}
