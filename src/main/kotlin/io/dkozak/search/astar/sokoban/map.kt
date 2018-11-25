package io.dkozak.search.astar.sokoban

typealias Location = Pair<Int, Int>

enum class Field {
    WALL,
    PATH
}


/**
 *
 * Example input:
 * 07 12 04
 * XXXXXXX
 * XG....X
 * X.....X
 * XGXG.GX
 * XXXX.XX
 * X.J...X
 * X.J...X
 * X..XXXX
 * X.J.JMX
 * X.....X
 * X...XXX
 * XXXXX
 */
fun parseMap(serializedMap: String): SokobanMap {
    val lines = serializedMap.split("\n")
    val header = lines[0]
    val map = lines.subList(1, lines.size)
    val (rowCount, colCount, diamondCount) = header.split(" ").map { it.toInt() }
    val fields = mutableListOf<MutableList<Field>>()
    val goals = mutableListOf<Location>()
    val cans = mutableListOf<Location>()
    var playerPosition = 0 to 0

    map.forEachIndexed { i, line ->
        val lineList = mutableListOf<Field>()
        line.forEachIndexed { j, char ->
            val location = i to j
            when (char) {
                'M' -> playerPosition = location
                'J' -> cans.add(location)
                'G' -> goals.add(location)
            }
            lineList.add(if (char != 'X' && char != ' ') Field.PATH else Field.WALL)
        }
        fields.add(lineList)
    }

    return SokobanMap(SokobanMap__Static(fields, goals), SokobanMap__Dynamic(playerPosition, cans))
}


fun isFinished(sokobanMap__Static: SokobanMap__Static, sokobanMap__Dynamic: SokobanMap__Dynamic) = sokobanMap__Static.goals == sokobanMap__Dynamic.canPositions


val SokobanMap.isFinished
    get() = isFinished(this.sokobanMap__Static, this.sokobanMap__Dynamic)

data class SokobanMap(
        val sokobanMap__Static: SokobanMap__Static,
        val sokobanMap__Dynamic: SokobanMap__Dynamic
) {

    override fun toString(): String = buildString {
        sokobanMap__Static.fields.forEachIndexed { i, line ->
            line.forEachIndexed { j, field ->
                val location = i to j
                when (location) {
                    sokobanMap__Dynamic.playerPosition -> append('M')
                    in sokobanMap__Dynamic.canPositions -> append('J')
                    in sokobanMap__Static.goals -> append('G')
                    else -> if (field == Field.PATH) append('.') else append('X')
                }
                if (j == line.size - 1) append('\n')
            }
        }
    }
}

data class SokobanMap__Static(
        val fields: List<List<Field>>,
        val goals: List<Location>
) {
    operator fun get(i: Int, j: Int): Field = if (i < 0 || j < 0 || i >= fields.size || j >= fields[0].size) {
        Field.WALL
    } else {
        fields[i][j]
    }

    operator fun get(location: Location) = this[location.first, location.second]
}

data class SokobanMap__Dynamic(
        val playerPosition: Location,
        val canPositions: List<Location>

) {

}