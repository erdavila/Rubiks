package erdavila.rubiks

object Orientation {
  private val clockwiseValues = Array(North, East, South, West)
}

abstract class Orientation {
  private lazy val index = Orientation.clockwiseValues.indexOf(this)

  lazy val clockwise        = Orientation.clockwiseValues((index + 1) % 4)
  lazy val opposite         = Orientation.clockwiseValues((index + 2) % 4)
  lazy val counterClockwise = Orientation.clockwiseValues((index + 3) % 4)

  def rotated(rotation: Rotation) = rotation match {
    case Clockwise        => clockwise
    case CounterClockwise => counterClockwise
  }
}

object North extends Orientation
object East  extends Orientation
object South extends Orientation
object West  extends Orientation
