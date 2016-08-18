package erdavila.rubiks

import erdavila.rubiks.Color.White
import erdavila.rubiks.FaceLabel._
import erdavila.rubiks.Rotation.Clockwise

class MockableFace extends Face(0, 3, null)

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
