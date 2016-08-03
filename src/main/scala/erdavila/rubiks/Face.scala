package erdavila.rubiks

class Face(firstId: Int, val size: Int, color: Color) {
  class Stripes {
    def apply(fromEdge: Orientation, offset: Int = 0): Array[Facelet] = {
      val rotation = fromEdge match {
        case North => None
        case East  => Some(CounterClockwise)
        case South => Some(Flipped)
        case West  => Some(Clockwise)
      }
      val mapSourceFromDestination = srcFromDestMapping(rotation)
      val mapSourceFromIndex = { index: Int => mapSourceFromDestination(offset, index) }

      Array.tabulate(size) { index =>
        val (srcRow, srcColumn) = mapSourceFromIndex(index)
        val facelet = facelets(srcRow)(srcColumn)
        rotation match {
          case Some(r) => facelet.rotated(r)
          case None    => facelet
        }
      }
    }
  }

  val facelets = Array.tabulate(size, size) {
    case (row, column) =>
      val id = firstId + row * size + column
      Facelet(id, color)
  }

  val stripes = new Stripes

  def rotate(rotation: Rotation): Unit = {
    val mapSourceFromDestination = srcFromDestMapping(Some(rotation))

    Array.tabulate(size, size) {
      case (dstRow, dstColumn) =>
        val (srcRow, srcColumn) = mapSourceFromDestination(dstRow, dstColumn)
        val srcFacelet = facelets(srcRow)(srcColumn)
        srcFacelet.rotated(rotation)
    } copyToArray facelets
  }

  private def srcFromDestMapping(rotation: Option[Rotation]) =
    rotation match {
      case Some(CounterClockwise) => { (dstRow: Int, dstColumn: Int) => (      dstColumn , compl(dstRow)   ) }
      case Some(Flipped)          => { (dstRow: Int, dstColumn: Int) => (compl(dstRow)   , compl(dstColumn)) }
      case Some(Clockwise)        => { (dstRow: Int, dstColumn: Int) => (compl(dstColumn),       dstRow    ) }
      case None                   => { (dstRow: Int, dstColumn: Int) => (      dstRow    ,       dstColumn ) }
    }

  private def compl(n: Int) = size - 1 - n
}
