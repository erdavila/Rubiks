package erdavila.rubiks

object Orientation {
  private val clockwiseValues = Array(North, East, South, West)
}

sealed abstract class Orientation {
  private lazy val index = Orientation.clockwiseValues.indexOf(this)

  lazy val clockwise        = Orientation.clockwiseValues((index + 1) % 4)
  lazy val opposite         = Orientation.clockwiseValues((index + 2) % 4)
  lazy val counterClockwise = Orientation.clockwiseValues((index + 3) % 4)

  def rotated(rotation: Rotation) = rotation match {
    case Clockwise        => clockwise
    case CounterClockwise => counterClockwise
    case Flipped          => opposite
  }
}

case object North extends Orientation
case object East  extends Orientation
case object South extends Orientation
case object West  extends Orientation
