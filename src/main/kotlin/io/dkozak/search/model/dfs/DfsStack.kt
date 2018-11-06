package io.dkozak.search.model.dfs

import io.dkozak.search.model.Node
import io.dkozak.search.model.OpenList
import java.util.*

class DfsStack<NodeType : Node> : OpenList<NodeType> {

    private val deque: Deque<NodeType> = LinkedList()

    override fun next(): NodeType = deque.pollFirst()

    override fun isEmpty(): Boolean = deque.isEmpty()

    override fun addNodes(nodes: List<NodeType>) {
        for (node in nodes.reversed()) {
            deque.addFirst(node)
        }
    }

    override fun toString(): String = deque.toString()
}