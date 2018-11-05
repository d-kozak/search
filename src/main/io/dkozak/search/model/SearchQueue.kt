package io.dkozak.search.model

interface SearchQueue {
    fun next(): Node
    fun addNodes(nodes: List<Node>)
    fun isEmpty(): Boolean

    fun addNode(node: Node) = addNodes(listOf(node))
}