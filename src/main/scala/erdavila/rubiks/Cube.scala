package erdavila.rubiks

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
