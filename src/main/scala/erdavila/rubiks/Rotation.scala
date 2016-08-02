package erdavila.rubiks

sealed abstract class Rotation

case object Clockwise extends Rotation
case object CounterClockwise extends Rotation
case object Flipped extends Rotation
