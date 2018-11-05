package io.dkozak.search.engine

import io.dkozak.search.model.EngineConfiguration
import io.dkozak.search.model.Node


fun <NodeType : Node> search(engineConfiguration: EngineConfiguration<NodeType>, logger: (String) -> Unit = {}) = engineConfiguration.executeSearch(logger)

private fun <NodeType : Node> EngineConfiguration<NodeType>.executeSearch(logger: (String) -> Unit): Node? {
    val queue = searchQueue
    queue.addNode(initialState)
    while (!queue.isEmpty()) {
        val nextNode = queue.next()
        logger("Exploring state $nextNode")
        if (isFinalState(nextNode)) {
            logger("$nextNode is a final state, returning")
            return nextNode
        }
        val children = expandNode(nextNode)
        logger("Expanded node and got these children $children")
        val filtered = children.filter(nodeFilter)
        logger("Children after filtering $filtered")
        queue.addNodes(filtered)
    }
    logger("Whole state space explored and no final node found, returning null")
    return null
}
