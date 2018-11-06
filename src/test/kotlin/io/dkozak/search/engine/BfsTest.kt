package io.dkozak.search.engine

import io.dkozak.search.model.Node
import io.dkozak.search.model.bfs.breadthFirstSearch
import org.junit.jupiter.api.Test

class NumNodeWithPath(
        val num: Int,
        val parent: Node? = null
) : Node {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NumNodeWithPath) return false

        if (num != other.num) return false

        return true
    }

    override fun hashCode(): Int {
        return num
    }

    override fun toString(): String {
        return "NumNodeWithPath(num=$num, parent=$parent)"
    }


}

class BfsTest {

    @Test
    fun `simpleNumberGame solution should be found, using bfs`() {
        val configuration = breadthFirstSearch(
                initialState = NumNodeWithPath(1),
                expandNode = { node ->
                    listOf(1, 2, 3, 4, 5).map {
                        NumNodeWithPath(it + node.num, node)
                    }
                },
                isFinalState = { node -> node.num == 42 }
        )
        val logger: (String) -> Unit = { msg -> println(msg) }
        val result = search(configuration, logger)
        println("Found result $result")
    }
}