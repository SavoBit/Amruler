package amruler

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

trait Specification extends WordSpec with Matchers with MockitoSugar with BeforeAndAfter {}
