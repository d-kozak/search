package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.SokobanNode
import io.dkozak.search.astar.sokoban.heuristic
import io.dkozak.search.astar.sokoban.parseMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HeuristicTest {

    @Test
    fun simpleGame() {
        val serializedMap = """
                        04 04 01
                        XXXXX
                        XMJGX
                        XXXXX
                        XXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        assertEquals(1, map.heuristic)
    }

    @Test
    fun littleMoreComplex() {
        val serializedMap = """
                        04 04 01
                        XXXXX
                        XMJGX
                        XJX.X
                        XGXXX
                        XXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        assertEquals(2, map.heuristic)
    }

    @Test
    fun littleMoreComplex2() {
        val serializedMap = """
                        04 04 01
                        XXXXXX
                        XMJ..X
                        XJX.GX
                        XGXXXX
                        XXXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        assertEquals(4, map.heuristic)
    }

}