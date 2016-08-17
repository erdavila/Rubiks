package erdavila.rubiks

import erdavila.rubiks.Orientation.North
import erdavila.rubiks.Rotation._

class Face(firstId: Int, val size: Int, color: Color) {
  class Stripes {
    def apply(fromEdge: Orientation, offset: Int = 0): Array[Facelet] = {
      val rotation = North - fromEdge
      val mapSourceFromDestination = srcFromDestMapping(rotation)
      val mapSourceFromIndex = { index: Int => mapSourceFromDestination(offset, index) }

      Array.tabulate(size) { index =>
        val (srcRow, srcColumn) = mapSourceFromIndex(index)
        val facelet = facelets(srcRow)(srcColumn)
        facelet.rotated(rotation)
      }
    }

    def update(fromEdge: Orientation, offset: Int = 0, stripe: Seq[Facelet]): Unit = {
      val rotation = fromEdge - North
      val mapSourceFromDestinationReversed = srcFromDestMapping(rotation)
      val mapDestinationFromIndexReversed = { index: Int => mapSourceFromDestinationReversed(index, offset) }

      indexes.foreach { index =>
        val (dstColumn, dstRow) = mapDestinationFromIndexReversed(index)
        facelets(dstRow)(dstColumn) = stripe(index).rotated(rotation)
      }
    }
  }

  val facelets: IndexedSeq[Array[Facelet]] = Array.tabulate(size, size) {
    case (row, column) =>
      val id = firstId + row * size + column
      Facelet(id, color)
  }

  val stripes = new Stripes

  private val indexes = 0 until size

  def rotate(rotation: Rotation): Unit = {
    val mapSourceFromDestination = srcFromDestMapping(rotation)

    val rotatedFacelets = Array.tabulate(size, size) {
      case (dstRow, dstColumn) =>
        val (srcRow, srcColumn) = mapSourceFromDestination(dstRow, dstColumn)
        val srcFacelet = facelets(srcRow)(srcColumn)
        srcFacelet.rotated(rotation)
    }

    indexes.foreach { row =>
      indexes.foreach { column =>
        facelets(row)(column) = rotatedFacelets(row)(column)
      }
    }
  }

  private def srcFromDestMapping(rotation: Rotation) =
    rotation match {
      case NoRotation       => { (dstRow: Int, dstColumn: Int) => (      dstRow    ,       dstColumn ) }
      case Clockwise        => { (dstRow: Int, dstColumn: Int) => (compl(dstColumn),       dstRow    ) }
      case Flipped          => { (dstRow: Int, dstColumn: Int) => (compl(dstRow)   , compl(dstColumn)) }
      case CounterClockwise => { (dstRow: Int, dstColumn: Int) => (      dstColumn , compl(dstRow)   ) }
    }

  private def compl(n: Int) = size - 1 - n
}
