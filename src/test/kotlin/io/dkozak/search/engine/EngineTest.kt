package io.dkozak.search.engine

import io.dkozak.search.model.EngineConfiguration
import io.dkozak.search.model.Node
import io.dkozak.search.model.bfs.FifoOpenList
import org.junit.jupiter.api.Test

data class NumNode(val num: Int) : Node

class EngineTest {

    @Test
    fun `simpleNumberGame solution should be found, no closed set`() {
        val configuration = EngineConfiguration(
                initialState = NumNode(1),
                openList = FifoOpenList(),
                expandNode = { node -> listOf(1, 2, 3, 4, 5).map { NumNode(it + node.num) } },
                nodeFilter = { _ -> true },
                isFinalState = { node -> node.num == 42 }
        )
        val logger: (String) -> Unit = { msg -> println(msg) }
        val result = search(configuration, logger)
        println("Found result $result")
    }

    @Test
    fun `simpleNumberGame solution should be found, with closed set`() {
        // notice how much faster this is :)
        val visitedNodes = mutableSetOf<Node>()
        val configuration = EngineConfiguration(
                initialState = NumNode(1),
                openList = FifoOpenList(),
                expandNode = { node -> listOf(1, 2, 3, 4, 5).map { NumNode(it + node.num) } },
                nodeFilter = { node -> visitedNodes.add(node) },
                isFinalState = { node -> node.num == 42 }
        )
        val logger: (String) -> Unit = { msg -> println(msg) }
        val result = search(configuration, logger)
        println("Found result $result")
    }
}