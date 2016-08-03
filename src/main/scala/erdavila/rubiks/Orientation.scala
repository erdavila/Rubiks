package erdavila.rubiks

object Orientation {
  private val clockwiseValues = Array(North, East, South, West)

  def clockwisely(index: Int) = clockwiseValues(Math.floorMod(index, 4))
}

sealed abstract class Orientation {
  private lazy val index = Orientation.clockwiseValues.indexOf(this)

  def rotated(rotation: Rotation) = this :+ rotation

  def :+(rotation: Rotation): Orientation = Orientation.clockwisely(index + rotation.clockwiseAmount)
  def :-(rotation: Rotation): Orientation = Orientation.clockwisely(index - rotation.clockwiseAmount)
  def -(orientation: Orientation): Rotation = Rotation.byClockwiseAmount(index - orientation.index)
}

case object North extends Orientation
case object East  extends Orientation
case object South extends Orientation
case object West  extends Orientation
