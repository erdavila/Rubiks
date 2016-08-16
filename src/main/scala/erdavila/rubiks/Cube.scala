package erdavila.rubiks

class Cube(facesProvider: (FaceLabel) => Face) {
  private val faces = FaceLabel.values
    .map { f => f -> facesProvider(f) }
    .toMap

  def rotateFace(faceLabel: FaceLabel, rotation: Rotation): Unit =
    faces(faceLabel).rotate(rotation)
}
