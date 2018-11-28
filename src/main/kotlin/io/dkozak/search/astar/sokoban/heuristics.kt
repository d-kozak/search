package io.dkozak.search.astar.sokoban

import java.util.*
import kotlin.collections.HashSet


val SokobanMap.isUsable: Boolean
    get() {
        return this.sokobanMap__Dynamic.canPositions.all { canPosition ->
            if (canPosition in this.sokobanMap__Static.goals)
                return true
            val neighbours = canPosition.neighbours
            for (current in 0 until neighbours.size) {
                val next = (current + 1) % neighbours.size
                if (this.sokobanMap__Static[neighbours[current]] == Field.WALL && this.sokobanMap__Static[neighbours[next]] == Field.WALL) {
                    return false
                }
            }
            true
        }
    }

val SokobanMap.heuristic: Int
    get() {
        val canPositions = this.sokobanMap__Dynamic.canPositions
        return canPositions.map { findNearestGoal(it, this) }
                .map { it.size - 1 }
                .sum()
    }


fun findNearestGoal(canPosition: Location, sokobanMap: SokobanMap): List<Location> {
    data class Node(val location: Location, val parent: Node? = null)

    val pathOf: (Node) -> List<Location> = {
        var currentNode: Node? = it
        val path = mutableListOf<Location>()
        while (currentNode != null) {
            path.add(currentNode.location)
            currentNode = currentNode.parent
        }
        path
    }

    val getNeighbours: (Node) -> List<Node> = { node ->
        node.location.neighbours.filter { sokobanMap.sokobanMap__Static[it] == Field.PATH }
                .map { Node(it, node) }

    }

    val openList = LinkedList<Node>()
    openList.add(Node(canPosition))
    val closedSed = HashSet<Node>()
    while (openList.isNotEmpty()) {
        val currentNode = openList.removeFirst()
        if (currentNode.location in sokobanMap.sokobanMap__Static.goals)
            return pathOf(currentNode)

        if (currentNode in closedSed) continue

        val neighbours = getNeighbours(currentNode)
        openList.addAll(neighbours)

    }
    throw IllegalArgumentException("No path found")
}
