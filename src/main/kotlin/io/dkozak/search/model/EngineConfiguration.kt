package io.dkozak.search.model

open class EngineConfiguration<NodeType : Node>
(
        val initialState: NodeType,
        val openList: OpenList<NodeType>,
        val expandNode: (NodeType) -> List<NodeType>,
        val nodeFilter: (NodeType) -> Boolean,
        val isFinalState: (NodeType) -> Boolean
)
