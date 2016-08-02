package erdavila.rubiks

class OrientationSpec extends UnitSpec {
  describe("North") {
    it("correctly relates to the other orientations") {
      North.clockwise        should be (East)
      North.counterClockwise should be (West)
      North.opposite         should be (South)
      North.rotated(Clockwise)        should be (East)
      North.rotated(CounterClockwise) should be (West)
    }
  }

  describe("East") {
    it("correctly relates to the other orientations") {
      East.clockwise        should be (South)
      East.counterClockwise should be (North)
      East.opposite         should be (West)
      East.rotated(Clockwise)        should be (South)
      East.rotated(CounterClockwise) should be (North)
    }
  }

  describe("South") {
    it("correctly relates to the other orientations") {
      South.clockwise        should be (West)
      South.counterClockwise should be (East)
      South.opposite         should be (North)
      South.rotated(Clockwise)        should be (West)
      South.rotated(CounterClockwise) should be (East)
    }
  }

  describe("West") {
    it("correctly relates to the other orientations") {
      West.clockwise        should be (North)
      West.counterClockwise should be (South)
      West.opposite         should be (East)
      West.rotated(Clockwise)        should be (North)
      West.rotated(CounterClockwise) should be (South)
    }
  }
}
