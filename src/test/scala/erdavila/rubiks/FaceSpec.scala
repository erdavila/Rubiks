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
      it("initializes the places with sequentially numbered Facelets") {
        val face = new Face(7, 3, Yellow)

        face.places should equal (
          facelets(
            Seq(( 7, Yellow, North), ( 8, Yellow, North), ( 9, Yellow, North)),
            Seq((10, Yellow, North), (11, Yellow, North), (12, Yellow, North)),
            Seq((13, Yellow, North), (14, Yellow, North), (15, Yellow, North))))
      }
    }
  }
}
