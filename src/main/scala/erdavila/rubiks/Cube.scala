package erdavila.rubiks

import erdavila.rubiks.Color._
import erdavila.rubiks.FaceLabel._

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
}
