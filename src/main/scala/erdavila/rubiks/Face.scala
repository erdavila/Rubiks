package erdavila.rubiks

class Face(firstId: Int, val size: Int, color: Color) {
  val facelets = Array.tabulate(size, size) {
    case (row, column) =>
      val id = firstId + row * size + column
      Facelet(id, color)
  }

  def rotate(rotation: Rotation): Unit = {
    val sourcePositionFor = rotation match {
      case Clockwise        => { (destRow: Int, destColumn: Int) => (size - 1 - destColumn, destRow) }
      case CounterClockwise => { (destRow: Int, destColumn: Int) => (destColumn, size - 1 - destRow) }
      case Flipped          => { (destRow: Int, destColumn: Int) => (size - 1 - destRow, size - 1 - destColumn) }
    }

    Array.tabulate(size, size) {
      case (destRow, destColumn) =>
        val (srcRow, srcColumn) = sourcePositionFor(destRow, destColumn)
        val srcFacelet = facelets(srcRow)(srcColumn)
        srcFacelet.rotated(rotation)
    } copyToArray facelets
  }
}
