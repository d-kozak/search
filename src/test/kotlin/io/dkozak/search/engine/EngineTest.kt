package io.dkozak.search.engine

import io.dkozak.search.model.EngineConfiguration
import io.dkozak.search.model.Node
import io.dkozak.search.model.QueueBasedSearchQueue
import org.junit.jupiter.api.Test

data class NumNode(val num: Int) : Node

class EngineTest {

    @Test
    fun `simpleNumberGame solution should be found`() {
        val configuration = EngineConfiguration(
                initialState = NumNode(1),
                searchQueue = QueueBasedSearchQueue(),
                expandNode = { node -> listOf(1, 2, 3, 4, 5).map { NumNode(it + node.num) } },
                nodeFilter = { node -> true },
                isFinalState = { node -> node.num == 42 }
        )
        val result = search(configuration, { msg -> println(msg) })
        println("Found result $result")
    }
}