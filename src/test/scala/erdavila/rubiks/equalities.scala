package erdavila.rubiks

import erdavila.rubiks.Color.{White, Green}
import erdavila.rubiks.FaceLabel._
import erdavila.rubiks.Orientation.South
import org.scalactic.Equality

object Equalities {
  implicit val faceEq = new Equality[Face] {
    def areEqual(a: Face, b: Any): Boolean =
      b match {
        case f: Face =>
          a.size == f.size  &&  a.facelets.flatten == f.facelets.flatten
        case _ => false
      }
  }

  implicit val cubeEq = new Equality[Cube] {
    import org.scalactic.TripleEquals._

    def areEqual(a: Cube, b: Any): Boolean =
      b match {
        case c: Cube =>
          a.size == c.size  &&
          FaceLabel.values.forall { fl => a.faces(fl) === c.faces(fl) }
        case _ => false
      }
    }
}

class EqualitiesSpec extends UnitSpec {
  describe("Equalities") {
    describe(".faceEq") {
      it("defines Faces equality") {
        def newFace(
            firstId: Int = 0,
            size: Int = 3,
            color: Color = White) =
              new Face(firstId, size, color)
        val faceA = newFace()
        val faceB = newFace()
        val faceDifferentFirstId = newFace(firstId = 1)
        val faceDifferentSize    = newFace(size = 2)
        val faceDifferentColor   = newFace(color = Green)
        val faceDifferentFacelet = {
          val f = newFace()
          f.facelets(1)(1) = Facelet(-1, White, South)
          f
        }

        import Equalities.faceEq

        faceA should equal (faceA)
        faceA should equal (faceB)
        faceA should not equal (faceDifferentFirstId)
        faceA should not equal (faceDifferentSize)
        faceA should not equal (faceDifferentColor)
        faceA should not equal (faceDifferentFacelet)
      }
    }

    describe(".cubeEq") {
      it("defines Cubes equality") {
        val cubeA = Cube(3)
        val cubeB = Cube(3)
        val cubeDifferentSize = Cube(2)
        val cubeDifferentFace = {
          val c = Cube(3)
          c.faces(F).facelets(1)(1) = Facelet(-1, White, South)
          c
        }

        import Equalities.cubeEq

        cubeA should equal (cubeA)
        cubeA should equal (cubeB)
        cubeA should not equal (cubeDifferentSize)
        cubeA should not equal (cubeDifferentFace)
      }
    }
  }
}
