package erdavila.rubiks

import erdavila.rubiks.FaceLabel._

class MockableFace extends Face(0, 3, null)

class CubeSpec extends UnitSpec {
  describe("Cube") {
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
