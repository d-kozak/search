package io.dkozak.search.model

interface SearchQueue<NodeType : Node> {
    fun next(): NodeType
    fun addNodes(nodes: List<NodeType>)
    fun isEmpty(): Boolean

    fun addNode(node: NodeType) = addNodes(listOf(node))
}