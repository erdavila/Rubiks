package erdavila.rubiks

import org.scalamock.scalatest.MockFactory
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FunSpec
import org.scalatest.Matchers
import org.scalatest.OneInstancePerTest

abstract class UnitSpec
  extends FunSpec
  with OneInstancePerTest
  with BeforeAndAfterEach
  with Matchers
  with MockFactory
