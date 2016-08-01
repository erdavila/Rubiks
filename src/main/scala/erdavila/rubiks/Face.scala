package erdavila.rubiks

class Face(firstId: Int, val size: Int, color: Color) {
  val places = Array.tabulate(size, size) {
    case (row, column) =>
      val id = firstId + row * size + column
      new Facelet(id, color)
  }
}
