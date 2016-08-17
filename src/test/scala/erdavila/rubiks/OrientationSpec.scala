package erdavila.rubiks

import erdavila.rubiks.Orientation._
import erdavila.rubiks.Rotation._

class OrientationObjectSpec extends UnitSpec {
  describe("Orientation") {
    describe(".clockwisely()") {
      it("returns an Orientation") {
        Orientation.clockwisely(-4) should be (North)
        Orientation.clockwisely(-3) should be (East)
        Orientation.clockwisely(-2) should be (South)
        Orientation.clockwisely(-1) should be (West)
        Orientation.clockwisely( 0) should be (North)
        Orientation.clockwisely( 1) should be (East)
        Orientation.clockwisely( 2) should be (South)
        Orientation.clockwisely( 3) should be (West)
        Orientation.clockwisely( 4) should be (North)
      }
    }
  }
}

class OrientationSpec extends UnitSpec {
  describe("North") {
    it("correctly relates to the other orientations") {
      North.rotated(NoRotation)       should be (North)
      North.rotated(Clockwise)        should be (East)
      North.rotated(Flipped)          should be (South)
      North.rotated(CounterClockwise) should be (West)
    }

    describe(".:+(Rotation)") {
      it("results in an Orientation") {
        North :+ NoRotation       should be (North)
        North :+ Clockwise        should be (East)
        North :+ Flipped          should be (South)
        North :+ CounterClockwise should be (West)
      }
    }

    describe(".:-(Rotation)") {
      it("results in an Orientation") {
        North :- NoRotation       should be (North)
        North :- Clockwise        should be (West)
        North :- Flipped          should be (South)
        North :- CounterClockwise should be (East)
      }
    }

    describe(".-(Orientation)") {
      it("results in a Rotation") {
        North - North should be (NoRotation)
        North - East  should be (CounterClockwise)
        North - South should be (Flipped)
        North - West  should be (Clockwise)
      }
    }
  }

  describe("East") {
    it("correctly relates to the other orientations") {
      East.rotated(NoRotation)       should be (East)
      East.rotated(Clockwise)        should be (South)
      East.rotated(Flipped)          should be (West)
      East.rotated(CounterClockwise) should be (North)
    }

    describe(".:+(Rotation)") {
      it("results in an Orientation") {
        East :+ NoRotation       should be (East)
        East :+ Clockwise        should be (South)
        East :+ Flipped          should be (West)
        East :+ CounterClockwise should be (North)
      }
    }

    describe(".:-(Rotation)") {
      it("results in an Orientation") {
        East :- NoRotation       should be (East)
        East :- Clockwise        should be (North)
        East :- Flipped          should be (West)
        East :- CounterClockwise should be (South)
      }
    }

    describe(".-(Orientation)") {
      it("results in a Rotation") {
        East - North should be (Clockwise)
        East - East  should be (NoRotation)
        East - South should be (CounterClockwise)
        East - West  should be (Flipped)
      }
    }
  }

  describe("South") {
    it("correctly relates to the other orientations") {
      South.rotated(NoRotation)       should be (South)
      South.rotated(Clockwise)        should be (West)
      South.rotated(Flipped)          should be (North)
      South.rotated(CounterClockwise) should be (East)
    }

    describe(".:+(Rotation)") {
      it("results in an Orientation") {
        South :+ NoRotation       should be (South)
        South :+ Clockwise        should be (West)
        South :+ Flipped          should be (North)
        South :+ CounterClockwise should be (East)
      }
    }

    describe(".:-(Rotation)") {
      it("results in an Orientation") {
        South :- NoRotation       should be (South)
        South :- Clockwise        should be (East)
        South :- Flipped          should be (North)
        South :- CounterClockwise should be (West)
      }
    }

    describe(".-(Orientation)") {
      it("results in a Rotation") {
        South - North should be (Flipped)
        South - East  should be (Clockwise)
        South - South should be (NoRotation)
        South - West  should be (CounterClockwise)
      }
    }
  }

  describe("West") {
    it("correctly relates to the other orientations") {
      West.rotated(NoRotation)       should be (West)
      West.rotated(Clockwise)        should be (North)
      West.rotated(Flipped)          should be (East)
      West.rotated(CounterClockwise) should be (South)
    }

    describe(".:+(Rotation)") {
      it("results in an Orientation") {
        West :+ NoRotation       should be (West)
        West :+ Clockwise        should be (North)
        West :+ Flipped          should be (East)
        West :+ CounterClockwise should be (South)
      }
    }

    describe(".:-(Rotation)") {
      it("results in an Orientation") {
        West :- NoRotation       should be (West)
        West :- Clockwise        should be (South)
        West :- Flipped          should be (East)
        West :- CounterClockwise should be (North)
      }
    }

    describe(".-(Orientation)") {
      it("results in a Rotation") {
        West - North should be (CounterClockwise)
        West - East  should be (Flipped)
        West - South should be (Clockwise)
        West - West  should be (NoRotation)
      }
    }
  }
}
