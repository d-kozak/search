package io.dkozak.search.astar.sokoban

import io.dkozak.search.astar.Node
import io.dkozak.search.astar.SimpleNode


enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    DUMMY;
}

class SokobanNode(val sokobanMap: SokobanMap, val direction: Direction = Direction.DUMMY) : SimpleNode("NULL", 0, isGoal = sokobanMap.isFinished) {

    override fun getChildren(): MutableList<Pair<Node, Int>> {
        val result = mutableListOf<Pair<Node, Int>>()

        val (i, j) = sokobanMap.sokobanMap__Dynamic.playerPosition

        val up = (i - 1 to j) to (i - 2 to j)
        val down = (i + 1 to j) to (i + 2 to j)
        val left = (i to j - 1) to (i to j - 2)
        val right = (i to j + 1) to (i to j + 2)
        for ((locations, direction) in listOf(up to Direction.UP, down to Direction.DOWN, left to Direction.LEFT, right to Direction.RIGHT)) {
            val (next, nextNext) = locations
            val field = sokobanMap.sokobanMap__Static[next]
            if (field == Field.WALL) continue
            if (next !in sokobanMap.sokobanMap__Dynamic.canPositions) {
                val newDynamic = sokobanMap.sokobanMap__Dynamic.copy(playerPosition = next)
                result.add(SokobanNode(SokobanMap(sokobanMap.sokobanMap__Static, newDynamic), direction) to 1)
            } else {
                if (sokobanMap.sokobanMap__Static[nextNext] != Field.WALL && nextNext !in sokobanMap.sokobanMap__Dynamic.canPositions) {
                    val canPositions = sokobanMap.sokobanMap__Dynamic.canPositions.toMutableList()
                    canPositions.remove(next)
                    canPositions.add(nextNext)
                    val newDynamic = sokobanMap.sokobanMap__Dynamic.copy(playerPosition = next, canPositions = canPositions)
                    result.add(SokobanNode(SokobanMap(sokobanMap.sokobanMap__Static, newDynamic), direction) to 1)
                }
            }
        }
        return result
    }

    override fun toString(): String = sokobanMap.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SokobanNode) return false
        if (!super.equals(other)) return false

        if (sokobanMap != other.sokobanMap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + sokobanMap.hashCode()
        return result
    }
}