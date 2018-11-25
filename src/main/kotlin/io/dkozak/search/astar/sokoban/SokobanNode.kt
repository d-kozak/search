package io.dkozak.search.astar.sokoban

import io.dkozak.search.astar.Node
import io.dkozak.search.astar.SimpleNode

class SokobanNode(val sokobanMap: SokobanMap) : SimpleNode("NULL", 1, isGoal = sokobanMap.isFinished) {


    override fun getChildren(): MutableList<Pair<Node, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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