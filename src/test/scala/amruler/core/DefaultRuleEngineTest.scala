package amruler.core

import amruler.Specification
import amruler.api.Rules
import org.mockito.Mockito._
import org.mockito.Matchers.any

class DefaultRuleEngineTest extends Specification {

  "DefaultRuleEngine" should {
    "Return a list of action" in {
      val rule1 = mock[DefaultRule[Int, String]]
      val rule2 = mock[DefaultRule[Int, String]]

      when(rule1.evaluate(5)).thenReturn(true)
      when(rule1.execute(5)).thenReturn(List("Rule1 action1", "Rule1 action2"))

      when(rule2.evaluate(5)).thenReturn(true)
      when(rule2.execute(5)).thenReturn(List("Rule2 action1", "Rule2 action2"))

      val ruleEngine = new DefaultRuleEngine[Int, String]

      val result = ruleEngine.fire(Rules(rule1, rule2), 5)

      result should not be None
      result.size shouldEqual 4
      result(0) shouldEqual "Rule1 action1"
      result(1) shouldEqual "Rule1 action2"
      result(2) shouldEqual "Rule2 action1"
      result(3) shouldEqual "Rule2 action2"
    }

    "Return ordered list of action" in {
      val rule1 = mock[DefaultRule[Int, String]]
      val rule2 = mock[DefaultRule[Int, String]]

      when(rule1.evaluate(5)).thenReturn(true)
      when(rule1.execute(5)).thenReturn(List("Rule1 action1", "Rule1 action2"))
      when(rule1.order).thenReturn(1)

      when(rule2.evaluate(5)).thenReturn(true)
      when(rule2.execute(5)).thenReturn(List("Rule2 action1", "Rule2 action2"))
      when(rule2.order).thenReturn(0)

      val ruleEngine = new DefaultRuleEngine[Int, String]

      val result = ruleEngine.fire(Rules(rule1, rule2), 5)

      result should not be None
      result.size shouldEqual 4
      result(0) shouldEqual "Rule2 action1"
      result(1) shouldEqual "Rule2 action2"
      result(2) shouldEqual "Rule1 action1"
      result(3) shouldEqual "Rule1 action2"
    }
  }

}
