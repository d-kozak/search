package io.dkozak.search.astar

import org.junit.jupiter.api.Test

class AStarTest {

    @Test
    fun tst() {
        val nodes = ('a'..'o').map { SimpleNode(name = it.toString(), h = 1) }.groupBy { it.name[0] }
        for ((char, dlist) in nodes) {
            val node = dlist[0]
            val value = char - 'a'
            if ((value - 3) % 4 != 0) {
                val toChar = (value + 1).toChar()
                val neighbor = nodes[toChar]!![0]
                node.children.add(neighbor to 3)
            }
            if (value < 12) {
                val toChar = (value + 4).toChar()
                val neighbor = nodes[toChar]!![0]
                node.children.add(neighbor to 3)
            }
        }
        println(nodes)
    }
}