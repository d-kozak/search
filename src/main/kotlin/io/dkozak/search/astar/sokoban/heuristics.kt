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
        return canToGoalsDistance(canPositions) + playerToNearestCanDistance()
    }

fun SokobanMap.playerToNearestCanDistance(): Int {
    val openList = LinkedList<Node>()
    openList.add(Node(this.sokobanMap__Dynamic.playerPosition))
    val closedSet = HashSet<Node>()
    while (openList.isNotEmpty()) {
        val currentState = openList.removeFirst()
        if (currentState.location in this.sokobanMap__Dynamic.canPositions)
            return pathOf(currentState).size - 2
        if (currentState in closedSet) continue
        val neighbours = getNeighbours(this, currentState)
        openList.addAll(neighbours)


    }
    throw IllegalArgumentException("No can found")

}

private fun SokobanMap.canToGoalsDistance(canPositions: List<Location>): Int {
    return canPositions.map { findNearestGoal(it, this) }
            .map { it.size - 1 }
            .sum()
}


fun findNearestGoal(canPosition: Location, sokobanMap: SokobanMap): List<Location> {
    val openList = LinkedList<Node>()
    openList.add(Node(canPosition))
    val closedSet = HashSet<Node>()
    while (openList.isNotEmpty()) {
        val currentState = openList.removeFirst()
        if (currentState.location in sokobanMap.sokobanMap__Static.goals)
            return pathOf(currentState)

        if (currentState in closedSet) continue

        val neighbours = getNeighbours(sokobanMap, currentState)
        openList.addAll(neighbours)

    }
    throw IllegalArgumentException("No path found")
}


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

val getNeighbours: (SokobanMap, Node) -> List<Node> = { map, node ->
    node.location.neighbours.filter { map.sokobanMap__Static[it] == Field.PATH }
            .map { Node(it, node) }

}