package io.dkozak.search.engine

import io.dkozak.search.model.bfs.breadthFirstSearch
import org.junit.jupiter.api.Test

class BfsTest {

    @Test
    fun `simpleNumberGame solution should be found, using bfs`() {
        val configuration = breadthFirstSearch(
                initialState = NumNode(1),
                expandNode = { node -> listOf(1, 2, 3, 4, 5).map { NumNode(it + node.num) } },
                isFinalState = { node -> node.num == 42 }
        )
        val logger: (String) -> Unit = { msg -> println(msg) }
        val result = search(configuration, logger)
        println("Found result $result")
    }
}