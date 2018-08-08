package amruler.core

import amruler.Specification

class DefaultRuleTest extends Specification {

  "DefaultRule" should {
    "Evaluates its condition" in {

      val condition = (num: Int) => num > 5
      val action = (num:Int) => s"This is action1 for $num"

      val rule = DefaultRule(condition, List(action))

      val result1 = rule.evaluate(6)
      val result2 = rule.evaluate(4)

      result1 shouldBe true
      result2 shouldBe false
    }

    "Executes its actions and return a list of action result" in {

      val condition = (num: Int) => num > 5
      val action1 = (num: Int) => s"This is action1 for $num"
      val action2 = (num: Int) => s"This is action2 for $num"

      val rule = DefaultRule[Int, String](condition, action1, action2)

      val result: List[String] = rule.execute(6)

      result should not be None
      result.size shouldBe 2
      result(0) shouldBe "This is action1 for 6"
      result(1) shouldBe "This is action2 for 6"
    }

  }

}
