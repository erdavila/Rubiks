package erdavila.rubiks

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

        face.facelets should equal (
          facelets(
            Seq(( 7, Yellow, North), ( 8, Yellow, North), ( 9, Yellow, North)),
            Seq((10, Yellow, North), (11, Yellow, North), (12, Yellow, North)),
            Seq((13, Yellow, North), (14, Yellow, North), (15, Yellow, North))))
      }
    }

    describe(".rotate()") {
      val face = new Face(7, 3, Yellow)

      describe("Clockwise") {
        it("rotates the facelets") {
          face.rotate(Clockwise)

          face.facelets should equal (
            facelets(
              Seq((13, Yellow, East), (10, Yellow, East), ( 7, Yellow, East)),
              Seq((14, Yellow, East), (11, Yellow, East), ( 8, Yellow, East)),
              Seq((15, Yellow, East), (12, Yellow, East), ( 9, Yellow, East))))
        }
      }

      describe("CounterClockwise") {
        it("rotates the facelets") {
          face.rotate(CounterClockwise)

          face.facelets should equal (
            facelets(
              Seq(( 9, Yellow, West), (12, Yellow, West), (15, Yellow, West)),
              Seq(( 8, Yellow, West), (11, Yellow, West), (14, Yellow, West)),
              Seq(( 7, Yellow, West), (10, Yellow, West), (13, Yellow, West))))
        }
      }
    }
  }
}
