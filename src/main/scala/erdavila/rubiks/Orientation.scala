package erdavila.rubiks

abstract class Orientation(val cardinalPoint: Char)

object North extends Orientation('N')
object South extends Orientation('S')
object East  extends Orientation('E')
object West  extends Orientation('W')
