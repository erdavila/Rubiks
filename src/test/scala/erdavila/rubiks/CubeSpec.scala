package erdavila.rubiks

import erdavila.rubiks.Color._
import erdavila.rubiks.Equalities.faceEq
import erdavila.rubiks.FaceLabel._
import erdavila.rubiks.Orientation.South
import erdavila.rubiks.Rotation.Clockwise

class MockableFace extends Face(0, 3, null)

class CubeObjectSpec extends UnitSpec {
  describe("Cube companion object") {
    describe(".apply()") {
      it("creates a Cube with the specified size") {
        val cube = Cube(3)

        cube.size should equal (3)
        cube.faces(F) should equal (new Face( 0, 3, Green))
        cube.faces(U) should equal (new Face( 9, 3, White))
        cube.faces(R) should equal (new Face(18, 3, Red))
        cube.faces(B) should equal (new Face(27, 3, Blue))
        cube.faces(D) should equal (new Face(36, 3, Yellow))
        cube.faces(L) should equal (new Face(45, 3, Orange))
      }
    }
  }
}

class CubeSpec extends UnitSpec {
  describe("Cube") {
    describe("constructor") {
      it("fails if the passed faces have unmatching sizes") {
        val faces = Map[FaceLabel, Face](
          F -> new Face(-1, 0, White),
          U -> new Face(-1, 1, White),
          R -> new Face(-1, 2, White),
          B -> new Face(-1, 3, White),
          D -> new Face(-1, 4, White),
          L -> new Face(-1, 5, White))

        an [IllegalArgumentException] should be thrownBy new Cube(faces)
      }
    }

    describe(".faces") {
      it("have the same faces passed to the constructor") {
        val faces = Map[FaceLabel, Face](
          F -> mock[MockableFace],
          U -> mock[MockableFace],
          R -> mock[MockableFace],
          B -> mock[MockableFace],
          D -> mock[MockableFace],
          L -> mock[MockableFace])

        val cube = new Cube(faces)

        cube.faces should equal (faces)
      }
    }

    describe(".size") {
      it("is the same as the faces'") {
        val theSameFace = new Face(0, 3, White)
        val faces = FaceLabel.values.map(_ -> theSameFace).toMap

        val cube = new Cube(faces)

        cube.size should equal (3)
      }
    }

    describe(".rotateFace()") {
      it("rotates a face") {
        val rightFace = stub[MockableFace]
        val dummyFace = mock[MockableFace]
        val faces = Map[FaceLabel, Face](
          F -> dummyFace,
          U -> dummyFace,
          R -> rightFace,
          B -> dummyFace,
          D -> dummyFace,
          L -> dummyFace)
        val cube = new Cube(faces)

        cube.rotateFace(R, Clockwise)

        (rightFace.rotate _).verify(Clockwise)
      }
    }
  }
}
