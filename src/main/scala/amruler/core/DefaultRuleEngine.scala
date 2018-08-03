package amruler.core

import amruler.api.{Rule, RuleEngine, Rules}


class DefaultRuleEngine[T, R] extends RuleEngine[T, R] {

  implicit def ruleToOrderedRule[T, R](thisRule: Rule[T, R]): Ordered[Rule[T, R]] =
    thatRule => thisRule.order.compareTo(thatRule.order)

  override def fire(rules: Rules[T, R], obj: T): List[R] =
    rules.stream.sorted.filter(_.evaluate(obj)).flatMap(_.execute(obj)).toList
}
