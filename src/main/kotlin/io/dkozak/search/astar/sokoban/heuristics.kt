package io.dkozak.search.astar.sokoban

import java.util.*
import kotlin.collections.HashSet

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
        val location = node.location
        val neighbourIndexes = listOf(location.first - 1 to location.second,
                location.first + 1 to location.second,
                location.first to location.second - 1,
                location.first to location.second + 1)
        neighbourIndexes.filter { sokobanMap.sokobanMap__Static[it] == Field.PATH }
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
