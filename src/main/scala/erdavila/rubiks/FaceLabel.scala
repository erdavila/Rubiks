package erdavila.rubiks

abstract sealed class FaceLabel

object FaceLabel {
  case object F extends FaceLabel
  case object U extends FaceLabel
  case object R extends FaceLabel
  case object B extends FaceLabel
  case object D extends FaceLabel
  case object L extends FaceLabel

  val values = Array[FaceLabel](F, U, R, B, D, L)
}
