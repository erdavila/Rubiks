package erdavila.rubiks

class FaceletSpec extends UnitSpec {
  describe("Facelet") {
    describe(".rotated()") {
      it("returns a new Facelet with rotated orientation") {
        val facelet = Facelet(7, Yellow, North)

        val rotatedFacelet = facelet.rotated(Clockwise)

        rotatedFacelet should equal (Facelet(7, Yellow, East))
      }
    }
  }
}
