package erdavila.rubiks

class FaceletSpec extends UnitSpec {
  describe("Facelet") {
    describe(".rotated()") {
      it("returns a new Facelet with rotated orientation") {
        val orientation = stub[Orientation]
        val rotatedOrientation = mock[Orientation]
        (orientation.rotated _).when(Clockwise).returns(rotatedOrientation)
        val facelet = Facelet(7, Yellow, orientation)

        val rotatedFacelet = facelet.rotated(Clockwise)

        rotatedFacelet should equal (Facelet(7, Yellow, rotatedOrientation))
      }
    }
  }
}
