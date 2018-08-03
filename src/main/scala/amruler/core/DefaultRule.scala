package amruler.core

import amruler.api.Rule


object DefaultRule {

  def apply[T, R](when: T => Boolean, actions: List[T => R]) =
    new DefaultRule[T, R](when = when, actions = actions)

  def apply[T, R](when: T => Boolean, actions: (T => R)*) =
    new DefaultRule[T, R](when = when, actions = actions.toList)

  def apply[T, R](name: String,  description: String, order: Int, when: T => Boolean, actions: List[T => R]) =
    new DefaultRule[T, R](name, description, order, when, actions)

  def apply[T, R](name: String,  description: String, order: Int, when: T => Boolean, actions: (T => R)* ) =
    new DefaultRule[T, R](name, description, order, when, actions.toList)
}

class DefaultRule[T, R](val name: String = "rule", val description: String = "description",
                        val order: Int = 0, when: T => Boolean,
                        actions: List[T => R]) extends Rule[T, R]{

  override def evaluate(obj: T) = when(obj)
  override def execute(obj: T) = actions.map(_.apply(obj))
}
