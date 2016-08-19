package erdavila.rubiks

import erdavila.rubiks.Color._
import erdavila.rubiks.Equalities.{cubeEq, faceEq}
import erdavila.rubiks.FaceLabel._
import erdavila.rubiks.Orientation._
import erdavila.rubiks.Rotation._

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

    describe(".edges") {
      it("has all orientations defined for every face label") {
        val faceLabels = Table("faceLabel", FaceLabel.values: _*)

        forAll(faceLabels) { fl =>
          Cube.edges(fl).keySet should contain theSameElementsAs (Orientation.clockwiseValues)
        }
      }

      it("is symmetric") {
        val adjacenciesPairs =
          for {
            fl <- FaceLabel.values
            or <- Orientation.clockwiseValues
          } yield (fl, or)
        val adjacencies = Table("adjacency", adjacenciesPairs: _*)

        forAll(adjacencies) { adjacencyA =>
          val (faceLabelA, orientationA) = adjacencyA
          val adjacencyB = Cube.edges(faceLabelA)(orientationA)
          val (faceLabelB, orientationB) = adjacencyB
          Cube.edges(faceLabelB)(orientationB) should equal (adjacencyA)
        }
      }

      it("defines the vertices") {
        val faceLabels = Table("faceLabel", F, B)
        val faceEdge = Table("face edge", Orientation.clockwiseValues: _*)

        forAll(faceLabels) { faceLabel =>
          val thisFaceAdjacencies = Cube.edges(faceLabel)

          forAll(faceEdge) { thisFaceEdgeToA =>
            val thisFaceEdgeToB = thisFaceEdgeToA :+ Clockwise

            val (faceALabel, faceAEdgeToThis) = thisFaceAdjacencies(thisFaceEdgeToA)
            val (faceBLabel, faceBEdgeToThis) = thisFaceAdjacencies(thisFaceEdgeToB)

            val faceAEdgeToB = faceAEdgeToThis :+ CounterClockwise
            val faceBEdgeToA = faceBEdgeToThis :+ Clockwise

            Cube.edges(faceALabel)(faceAEdgeToB) should equal (faceBLabel, faceBEdgeToA)
            Cube.edges(faceBLabel)(faceBEdgeToA) should equal (faceALabel, faceAEdgeToB)
          }
        }
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

    describe(".rotateLayer()") {
      describe("Clockwise") {
        it("rotates Faces stripes") {
          val cube = Cube(3)

          cube.rotateLayer(FaceLabel.F, 0, Clockwise)

          val expectedCube = Cube(3)
          val stripeUToR = expectedCube.faces(U).stripes(South, 0)
          val stripeRToD = expectedCube.faces(R).stripes(West , 0)
          val stripeDToL = expectedCube.faces(D).stripes(North, 0)
          val stripeLToU = expectedCube.faces(L).stripes(East , 0)
          expectedCube.faces(U).stripes(South, 0) = stripeLToU
          expectedCube.faces(R).stripes(West , 0) = stripeUToR
          expectedCube.faces(D).stripes(North, 0) = stripeRToD
          expectedCube.faces(L).stripes(East , 0) = stripeDToL
          cube should equal (expectedCube)
        }
      }

      describe("CounterClockwise") {
        it("rotates Faces stripes") {
          val cube = Cube(3)

          cube.rotateLayer(FaceLabel.R, 1, CounterClockwise)

          val expectedCube = Cube(3)
          val stripeFToD = expectedCube.faces(F).stripes(East, 1)
          val stripeUToF = expectedCube.faces(U).stripes(East, 1)
          val stripeBToU = expectedCube.faces(B).stripes(West, 1)
          val stripeDToB = expectedCube.faces(D).stripes(East, 1)
          expectedCube.faces(F).stripes(East, 1) = stripeUToF
          expectedCube.faces(U).stripes(East, 1) = stripeBToU
          expectedCube.faces(B).stripes(West, 1) = stripeDToB
          expectedCube.faces(D).stripes(East, 1) = stripeFToD
          cube should equal (expectedCube)
        }
      }
    }
  }
}
