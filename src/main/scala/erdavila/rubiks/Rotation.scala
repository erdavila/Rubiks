package erdavila.rubiks

object Rotation {
  val clockwiseValues = Array(NoRotation, Clockwise, Flipped, CounterClockwise)

  def byClockwiseAmount(amount: Int) = clockwiseValues(Math.floorMod(amount, 4))
}

sealed abstract class Rotation(val clockwiseAmount: Int)

case object NoRotation extends Rotation(0)
case object Clockwise extends Rotation(1)
case object Flipped extends Rotation(2)
case object CounterClockwise extends Rotation(3)
