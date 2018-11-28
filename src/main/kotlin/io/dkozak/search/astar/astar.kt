package io.dkozak.search.astar

import java.util.*
import kotlin.collections.HashSet

// just simple example of A* algorithm

interface Node : Comparable<Node> {
    var h: Int
    var g: Int

    val total
        get() = g + h

    var parent: Node?

    val isGoal: Boolean

    val isUsable: Boolean

    val name: String

    fun getChildren(): MutableList<Pair<Node, Int>>

    override fun compareTo(other: Node): Int = Integer.compare(this.total, other.total)
}

abstract class SimpleNode(
        override val name: String,
        override var h: Int,
        override var g: Int = -1,
        override val isGoal: Boolean = false,
        override var parent: Node? = null
) : Node {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SimpleNode) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}


val Node.path: List<Node>
    get() {
        var currentNode: Node? = this
        val list = mutableListOf<Node>()
        while (currentNode != null) {
            list.add(0, currentNode)
            currentNode = currentNode.parent
        }
        return list
    }

fun <T> PriorityQueue<T>.findNode(node: T): T? = this.firstOrNull { it == node }


fun search(startNode: Node): List<Node> {
    val openSet = PriorityQueue<Node>()
    openSet.add(startNode)

    val closedSet = HashSet<Node>()

    var count = 1
    var alreadyExploredStatesCount = 0
    var invalidStatesCount = 0
    while (openSet.isNotEmpty()) {
        val currentNode = openSet.poll()
        println("Processing state no ${count++}/${openSet.size}, depth: ${currentNode.path.size}, heuristics ${currentNode.h}, total ${currentNode.total}")
        println("States excluded because of closed set $alreadyExploredStatesCount")
        println("Invalid states $invalidStatesCount")
        println(currentNode)

        if (currentNode.isGoal)
            return currentNode.path

        if (currentNode in closedSet) {
            print(".\n")
            alreadyExploredStatesCount++
            continue
        }
        closedSet.add(currentNode)

        for ((child, distance) in currentNode.getChildren()) {

            if (!child.isUsable) {
                print("!\n")
                invalidStatesCount++
                continue
            }

            val newG = currentNode.g + distance
            if (child in openSet) {
                val nodeInQueue = openSet.findNode(child)!!
                openSet.remove(nodeInQueue)
                nodeInQueue.parent = currentNode
                nodeInQueue.g = newG
                openSet.add(nodeInQueue)
            } else {
                child.g = newG
                child.parent = currentNode
                openSet.add(child)
            }
        }
    }

    return emptyList()
}