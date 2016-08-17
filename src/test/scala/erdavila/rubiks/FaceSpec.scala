package erdavila.rubiks

import erdavila.rubiks.Color._
import erdavila.rubiks.Orientation._
import erdavila.rubiks.Rotation._

class FaceSpec extends UnitSpec {
  type FaceletArgs = (Int, Color, Orientation)

  def facelets(faceletsArgsRows: Seq[FaceletArgs]*) =
    faceletsArgsRows map { argsRow =>
      argsRow map {
        case (firstId, color, orientation) => Facelet(firstId, color, orientation)
      }
    }

  describe("Face") {
    describe("constructor") {
      it("initializes the facelets with sequential ids and the passed color and orientation") {
        val face = new Face(7, 3, Yellow)

        face.facelets should contain theSameElementsInOrderAs
          facelets(
            Seq(( 7, Yellow, North), ( 8, Yellow, North), ( 9, Yellow, North)),
            Seq((10, Yellow, North), (11, Yellow, North), (12, Yellow, North)),
            Seq((13, Yellow, North), (14, Yellow, North), (15, Yellow, North)))
      }
    }

    describe(".facelets") {
      val face = new Face(7, 3, Yellow)

      it("can not have structure changed") {
        "face.facelets(0) += null" shouldNot compile
        "face.facelets(0)(0) += null" shouldNot compile
      }

      it("can have assignment only on second dimension") {
        "face.facelets(0) = null" shouldNot compile
        "face.facelets(0)(0) = null" should compile
      }
    }

    describe(".rotate()") {
      val face = new Face(7, 3, Yellow)

      describe("Clockwise") {
        it("rotates the facelets") {
          face.rotate(Clockwise)

          face.facelets should contain theSameElementsInOrderAs
            facelets(
              Seq((13, Yellow, East), (10, Yellow, East), ( 7, Yellow, East)),
              Seq((14, Yellow, East), (11, Yellow, East), ( 8, Yellow, East)),
              Seq((15, Yellow, East), (12, Yellow, East), ( 9, Yellow, East)))
        }
      }

      describe("CounterClockwise") {
        it("rotates the facelets") {
          face.rotate(CounterClockwise)

          face.facelets should contain theSameElementsInOrderAs
            facelets(
              Seq(( 9, Yellow, West), (12, Yellow, West), (15, Yellow, West)),
              Seq(( 8, Yellow, West), (11, Yellow, West), (14, Yellow, West)),
              Seq(( 7, Yellow, West), (10, Yellow, West), (13, Yellow, West)))
        }
      }

      describe("Flip") {
        it("rotates the facelets") {
          face.rotate(Flipped)

          face.facelets should contain theSameElementsInOrderAs
            facelets(
              Seq((15, Yellow, South), (14, Yellow, South), (13, Yellow, South)),
              Seq((12, Yellow, South), (11, Yellow, South), (10, Yellow, South)),
              Seq(( 9, Yellow, South), ( 8, Yellow, South), ( 7, Yellow, South)))
        }
      }
    }

    describe(".Stripes") {
      val face = new Face(7, 3, Yellow)

      describe(".apply()") {
        describe("North") {
          it("returns a stripe with the facelets correctly rotated") {
            val stripe = face.stripes(fromEdge = North, offset = 0)

            stripe should equal(
              facelets(Seq(( 7, Yellow, North), ( 8, Yellow, North), ( 9, Yellow, North))).head)
          }
        }

        describe("East") {
          it("returns a stripe with the facelets correctly rotated") {
            val stripe = face.stripes(fromEdge = East, offset = 0)

            stripe should equal(
              facelets(Seq(( 9, Yellow, West), (12, Yellow, West), (15, Yellow, West))).head)
          }
        }

        describe("South") {
          it("returns a stripe with the facelets correctly rotated") {
            val stripe = face.stripes(fromEdge = South, offset = 1)

            stripe should equal(
              facelets(
                Seq((12, Yellow, South), (11, Yellow, South), (10, Yellow, South))).head)
          }
        }

        describe("West") {
          it("returns a stripe with the facelets correctly rotated") {
            val stripe = face.stripes(fromEdge = West, offset = 2)

            stripe should equal(
              facelets(
                Seq((15, Yellow, East), (12, Yellow, East), ( 9, Yellow, East))).head)
          }
        }
      }

      describe(".update()") {
        val srcFacelets = Array(Facelet(20, Red, East), Facelet(21, Blue, South), Facelet(22, Orange, West))

        describe("North") {
          it("sets a stripe") {
            face.stripes(North, 0) = srcFacelets

            face.facelets should contain theSameElementsInOrderAs
              facelets(
                Seq((20, Red   , East ), (21, Blue  , South), (22, Orange, West )),
                Seq((10, Yellow, North), (11, Yellow, North), (12, Yellow, North)),
                Seq((13, Yellow, North), (14, Yellow, North), (15, Yellow, North)))
          }
        }

        describe("East") {
          it("sets a stripe") {
            face.stripes(East, 0) = srcFacelets

            face.facelets should contain theSameElementsInOrderAs
              facelets(
                Seq(( 7, Yellow, North), ( 8, Yellow, North), (20, Red   , South)),
                Seq((10, Yellow, North), (11, Yellow, North), (21, Blue  , West )),
                Seq((13, Yellow, North), (14, Yellow, North), (22, Orange, North)))
          }
        }

        describe("South") {
          it("sets a stripe") {
            face.stripes(South, 1) = srcFacelets

            face.facelets should contain theSameElementsInOrderAs
              facelets(
                Seq(( 7, Yellow, North), ( 8, Yellow, North), ( 9, Yellow, North)),
                Seq((22, Orange, East ), (21, Blue  , North), (20, Red   , West )),
                Seq((13, Yellow, North), (14, Yellow, North), (15, Yellow, North)))
          }
        }

        describe("West") {
          it("sets a stripe") {
            face.stripes(West, 2) = srcFacelets

            face.facelets should contain theSameElementsInOrderAs
              facelets(
                Seq(( 7, Yellow, North), ( 8, Yellow, North), (22, Orange, South)),
                Seq((10, Yellow, North), (11, Yellow, North), (21, Blue  , East )),
                Seq((13, Yellow, North), (14, Yellow, North), (20, Red   , North)))
          }
        }
      }
    }
  }
}
