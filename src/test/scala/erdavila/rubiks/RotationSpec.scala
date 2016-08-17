package erdavila.rubiks

import erdavila.rubiks.Rotation._

class RotationObjectSpec extends UnitSpec {
  describe("Rotation") {
    describe(".byClockwiseAmount()") {
      it("returns an Rotation") {
        Rotation.byClockwiseAmount(-4) should be (NoRotation)
        Rotation.byClockwiseAmount(-3) should be (Clockwise)
        Rotation.byClockwiseAmount(-2) should be (Flipped)
        Rotation.byClockwiseAmount(-1) should be (CounterClockwise)
        Rotation.byClockwiseAmount( 0) should be (NoRotation)
        Rotation.byClockwiseAmount( 1) should be (Clockwise)
        Rotation.byClockwiseAmount( 2) should be (Flipped)
        Rotation.byClockwiseAmount( 3) should be (CounterClockwise)
        Rotation.byClockwiseAmount( 4) should be (NoRotation)
      }
    }
  }
}
