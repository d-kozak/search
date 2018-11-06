package io.dkozak.search.model.dfs

import io.dkozak.search.model.EngineConfiguration
import io.dkozak.search.model.Node

fun <NodeType : Node> depthFirstSearch(initialState: NodeType,
                                       expandNode: (NodeType) -> List<NodeType>,
                                       isFinalState: (NodeType) -> Boolean): EngineConfiguration<NodeType> {
    val closedSet = mutableSetOf<NodeType>()
    return EngineConfiguration(
            initialState,
            DfsStack(),
            expandNode,
            { node: NodeType -> closedSet.add(node) },
            isFinalState
    )
}