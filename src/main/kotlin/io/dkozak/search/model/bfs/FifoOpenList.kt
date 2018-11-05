package io.dkozak.search.model.bfs

import io.dkozak.search.model.Node
import io.dkozak.search.model.OpenList
import java.util.*

class FifoOpenList<NodeType : Node> : OpenList<NodeType> {
    private val queue: Deque<NodeType> = LinkedList()

    override fun next(): NodeType = queue.poll()

    override fun addNodes(nodes: List<NodeType>) = nodes.forEach { queue.addLast(it) }

    override fun isEmpty(): Boolean = queue.isEmpty()
}