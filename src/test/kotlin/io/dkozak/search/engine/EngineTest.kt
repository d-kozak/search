package io.dkozak.search.engine

import io.dkozak.search.model.EngineConfiguration
import io.dkozak.search.model.FifoOpenList
import io.dkozak.search.model.Node
import org.junit.jupiter.api.Test

data class NumNode(val num: Int) : Node

class EngineTest {

    @Test
    fun `simpleNumberGame solution should be found`() {
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
}