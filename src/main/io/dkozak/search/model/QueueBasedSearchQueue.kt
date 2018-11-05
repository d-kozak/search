package io.dkozak.search.model

import java.util.*

class QueueBasedSearchQueue<NodeType : Node> : SearchQueue<NodeType> {
    private val queue: Deque<NodeType> = LinkedList()

    override fun next(): NodeType = queue.poll()

    override fun addNodes(nodes: List<NodeType>) = nodes.forEach { queue.addLast(it) }

    override fun isEmpty(): Boolean = queue.isEmpty()
}