package io.dkozak.search.astar.sokoban

typealias Location = Pair<Int, Int>

sealed class Field {
    object Wall : Field()
    class Path(val isDiamondGoal: Boolean, var hasDiamond: Boolean) : Field()
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
    val fields = map.map {
        it.asSequence()
                .map {
                    when (it) {
                        '.' -> Field.Path(isDiamondGoal = false, hasDiamond = false)
                        'M' -> Field.Path(isDiamondGoal = false, hasDiamond = false)
                        'G' -> Field.Path(isDiamondGoal = true, hasDiamond = false)
                        'J' -> Field.Path(isDiamondGoal = false, hasDiamond = true)
                        else -> Field.Wall
                    }
                }.toList()
    }
    val playerPosition = findPlayerPosition(map)
    return SokobanMap(playerPosition, fields)
}

private fun findPlayerPosition(map: List<String>): Location {
    map.forEachIndexed { i, line ->
        line.forEachIndexed { j, char ->
            if (char == 'M') return i to j
        }
    }
    throw IllegalArgumentException("Robot not found")
}


data class SokobanMap(
        private val playerPosition: Location,
        private val fields: List<List<Field>>
) {

    operator fun get(i: Int, j: Int): Field = if (i < 0 || j < 0 || i >= fields.size || j >= fields[0].size) {
        Field.Wall
    } else {
        fields[i][j]
    }

    val diamondGoals: List<Field.Path>
        get() = fields.asSequence()
                .flatMap { it.asSequence() }
                .filter { it is Field.Path }
                .map { it as Field.Path }
                .filter { it.isDiamondGoal }
                .toList()

    val isFinished get() = diamondGoals.all { it.hasDiamond }


    override fun toString(): String = buildString {
        fields.forEachIndexed { i, line ->
            line.forEachIndexed { j, field ->
                if (playerPosition.first == i && playerPosition.second == j) {
                    append('M')
                } else {
                    if (field is Field.Path) {
                        when {
                            field.hasDiamond -> append('J')
                            field.isDiamondGoal -> append('G')
                            else -> append('.')
                        }
                    } else {
                        append('X')
                    }
                }
                if (j == line.size - 1) append('\n')
            }
        }
    }

}