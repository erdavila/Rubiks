package erdavila.rubiks

import erdavila.rubiks.Color._
import erdavila.rubiks.FaceLabel._
import erdavila.rubiks.Orientation._

object Cube {
  def apply(size: Int): Cube = {
    val faceletsPerFace = size * size
    val faces = Map[FaceLabel, Face](
      F -> new Face(0 * faceletsPerFace, size, Green),
      U -> new Face(1 * faceletsPerFace, size, White),
      R -> new Face(2 * faceletsPerFace, size, Red),
      B -> new Face(3 * faceletsPerFace, size, Blue),
      D -> new Face(4 * faceletsPerFace, size, Yellow),
      L -> new Face(5 * faceletsPerFace, size, Orange))
    new Cube(faces)
  }

  val edges =
    Seq(
      ((F, North), (U, South)),
      ((F, East ), (R, West )),
      ((F, South), (D, North)),
      ((F, West ), (L, East )),
      ((U, North), (B, North)),
      ((U, East ), (R, North)),
      ((U, West ), (L, North)),
      ((R, East ), (B, West )),
      ((R, South), (D, East )),
      ((B, East ), (L, West )),
      ((B, South), (D, South)),
      ((D, West ), (L, South)))
    .flatMap {
      case (adjacencyA, adjacencyB) =>
        Seq(
          adjacencyA -> adjacencyB,
          adjacencyB -> adjacencyA)
    }
    .foldLeft(FaceLabel.values.map(_ -> Map.empty[Orientation, (FaceLabel, Orientation)]).toMap) {
      case (edges, ((adjacentFaceLabel, adjacentFaceEdgeOrientation), adjacency)) =>
        val faceEdges = edges(adjacentFaceLabel)
        val updatedFaceEdges = faceEdges + (adjacentFaceEdgeOrientation -> adjacency)
        edges + (adjacentFaceLabel -> updatedFaceEdges)
    }
}

class Cube(facesProvider: (FaceLabel) => Face) {
  val faces = FaceLabel.values
    .map { f => f -> facesProvider(f) }
    .toMap

  val size = {
    val anyFace = faces.values.head
    val sz = anyFace.size
    if (!faces.values.forall(_.size == sz)) {
      throw new IllegalArgumentException("All faces must have same size")
    }
    sz
  }

  def rotateFace(faceLabel: FaceLabel, rotation: Rotation): Unit =
    faces(faceLabel).rotate(rotation)

  def rotateLayer(faceLabel: FaceLabel, offset: Int, rotation: Rotation): Unit = {
    val faceEdges = Cube.edges(faceLabel)

    val faceAdjacencies = Orientation.clockwiseValues
      .map { orientation =>
        val (adjacentFaceLabel, adjacentFaceEdgeOrientation) = faceEdges(orientation)
        val adjacentFace = faces(adjacentFaceLabel)
        orientation -> (adjacentFace, adjacentFaceEdgeOrientation)
      }
      .toMap

    val stripes = Orientation.clockwiseValues
      .map { orientation =>
        val (adjacentFace, adjacentFaceEdgeOrientation) = faceAdjacencies(orientation)
        orientation -> adjacentFace.stripes(adjacentFaceEdgeOrientation, offset)
      }
      .toMap

    val rotatedStripes = stripes
      .map {
        case (orientation, stripe) =>
          val newOrientation = orientation :+ rotation
          newOrientation -> stripe
      }

    Orientation.clockwiseValues foreach { orientation =>
      val (adjacentFace, adjacentFaceEdgeOrientation) = faceAdjacencies(orientation)
      adjacentFace.stripes(adjacentFaceEdgeOrientation, offset) = rotatedStripes(orientation)
    }
  }
}
