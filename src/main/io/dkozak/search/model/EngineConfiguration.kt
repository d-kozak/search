package io.dkozak.search.model

class EngineConfiguration<NodeType : Node>
(
        val initialState: NodeType,
        val searchQueue: SearchQueue<NodeType>,
        val expandNode: (NodeType) -> List<NodeType>,
        val nodeFilter: (NodeType) -> Boolean,
        val isFinalState: (NodeType) -> Boolean
)
