package erdavila.rubiks

import erdavila.rubiks.Orientation.North

case class Facelet(id: Int, color: Color, orientation: Orientation = North) {
  def rotated(rotation: Rotation) =
    Facelet(id, color, orientation.rotated(rotation))
}
