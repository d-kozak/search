package io.dkozak.search.model.bfs

import io.dkozak.search.model.EngineConfiguration
import io.dkozak.search.model.Node


fun <NodeType : Node> breadthFirstSearch(initialState: NodeType,
                                         expandNode: (NodeType) -> List<NodeType>,
                                         isFinalState: (NodeType) -> Boolean): EngineConfiguration<NodeType> {
    val closedSet = mutableSetOf<NodeType>()
    return EngineConfiguration(
            initialState,
            FifoOpenList(),
            expandNode,
            { node -> closedSet.add(node) },
            isFinalState
    )
}