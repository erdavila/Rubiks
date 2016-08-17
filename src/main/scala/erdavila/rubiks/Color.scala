package erdavila.rubiks

sealed abstract class Color(val letter: Char)

object Color {
  case object White  extends Color('W')
  case object Green  extends Color('G')
  case object Red    extends Color('R')
  case object Blue   extends Color('B')
  case object Orange extends Color('O')
  case object Yellow extends Color('Y')
}
