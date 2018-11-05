package io.dkozak.search.model

class EngineConfiguration
(
        val initialState: Node,
        val searchQueue: SearchQueue,
        val expandNode: (Node) -> List<Node>,
        val nodeFilter: (Node) -> Boolean,
        val isFinalState: (Node) -> Boolean
)
