package amruler.api

trait Rule[T, R] {
  def name: String
  def description: String
  def order: Int
  def evaluate(obj: T): Boolean
  def execute(obj: T): List[R]
}
