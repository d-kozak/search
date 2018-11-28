package io.dkozak.search.astar.sokoban

import java.util.*
import kotlin.collections.HashSet


val SokobanMap.isUsable: Boolean
    get() {
        return this.heuristic != -1 && this.sokobanMap__Dynamic.canPositions.all { canPosition ->
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
        try {
            val canPositions = this.sokobanMap__Dynamic.canPositions
            return canToGoalsDistance(canPositions) + playerToNearestCanDistance()
        } catch (ex: IllegalArgumentException) {
            println("Could not find a path to goal,returning a very high value")
            return -1
        }
    }

fun SokobanMap.playerToNearestCanDistance(): Int {
    val openList = LinkedList<Node>()
    openList.add(Node(this.sokobanMap__Dynamic.playerPosition))
    val closedSet = HashSet<Location>()
    while (openList.isNotEmpty()) {
        val currentState = openList.removeFirst()
        if (currentState.location in this.sokobanMap__Dynamic.canPositions)
            return pathOf(currentState).size - 2
        if (currentState.location in closedSet) continue
        closedSet.add(currentState.location)
        val neighbours = getNeighbours(this, currentState).map { Node(it.first, currentState) }
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
    val closedSet = HashSet<Location>()
    while (openList.isNotEmpty()) {
        val currentState = openList.removeFirst()
        if (currentState.location in sokobanMap.sokobanMap__Static.goals)
            return pathOf(currentState)

        if (currentState.location in closedSet) continue
        closedSet.add(currentState.location)

        val neighbours = getNeighbours(sokobanMap, currentState).filter { isReachableFrom(it.second, currentState, sokobanMap) }
        openList.addAll(neighbours.map { Node(it.first, currentState) })

    }
    throw IllegalArgumentException("No path found for can at $canPosition on map \n$sokobanMap")
}

fun isReachableFrom(direction: Direction, currentState: Node, sokobanMap: SokobanMap): Boolean {
    val (x, y) = currentState.location
    return when (direction) {
        Direction.UP -> sokobanMap.sokobanMap__Static[x + 1, y] == Field.PATH
        Direction.DOWN -> sokobanMap.sokobanMap__Static[x - 1, y] == Field.PATH
        Direction.RIGHT -> sokobanMap.sokobanMap__Static[x, y - 1] == Field.PATH
        Direction.LEFT -> sokobanMap.sokobanMap__Static[x, y + 1] == Field.PATH
        Direction.DUMMY -> throw IllegalArgumentException("Dummy not allowed")
    }
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

val getNeighbours: (SokobanMap, Node) -> List<Pair<Location, Direction>> = { map, node ->
    node.location.neighboursAndDirections.filter { map.sokobanMap__Static[it.first] == Field.PATH }


}