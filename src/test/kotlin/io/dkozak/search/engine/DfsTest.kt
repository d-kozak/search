package io.dkozak.search.engine

import io.dkozak.search.model.dfs.depthFirstSearch
import org.junit.jupiter.api.Test

class DfsTest {
    @Test
    fun `simpleNumberGame solution should be found, using dfs`() {
        val configuration = depthFirstSearch(
                initialState = NumNode(1),
                expandNode = { node ->
                    if (node.num > 100) emptyList()
                    else {
                        listOf(1, 2, 3, 4, 5).map {
                            NumNode(it + node.num)
                        }
                    }
                },
                isFinalState = { node -> node.num == 42 }
        )
        val logger: (String) -> Unit = { msg -> println(msg) }
        val result = search(configuration, logger)
        println("Found result $result")
    }
}