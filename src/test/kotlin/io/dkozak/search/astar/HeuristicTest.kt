package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.SokobanNode
import io.dkozak.search.astar.sokoban.heuristic
import io.dkozak.search.astar.sokoban.isUsable
import io.dkozak.search.astar.sokoban.parseMap
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HeuristicTest {


    @Test
    fun isUsableTest() {
        val serializedMap = """
            04 04 01
            XXXXXXX
            XG....X
            X.....X
            XGXG.GX
            XXXX.XX
            X...J.X
            X..M..X
            X.JXXXX
            X....JX
            X.....X
            XJ..XXX
            XXXXXXX
        """.trimIndent()
        val map = parseMap(serializedMap)
        assertFalse(map.isUsable)
    }

    @Test
    fun isUsableTest2() {
        val serializedMap = """
            04 04 01
            XXXXXXX
            XG....X
            X.....X
            XGXG.GX
            XXXX.XX
            X...J.X
            X..M..X
            X.JXXXX
            X...J.X
            X.....X
            XJ..XXX
            XXXXXXX
        """.trimIndent()
        val map = parseMap(serializedMap)
        assertFalse(map.isUsable)
    }

    @Test
    fun isUsableTest3() {
        val serializedMap = """
            04 04 01
            XXXXXXX
            XG....X
            X.....X
            XGXG.GX
            XXXX.XX
            X...J.X
            X..M..X
            X.JXXXX
            X...J.X
            XJ....X
            X...XXX
            XXXXXXX
        """.trimIndent()
        val map = parseMap(serializedMap)
        assertTrue(map.isUsable)
    }

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
        assertTrue(map.isUsable)
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
        assertTrue(map.isUsable)
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
        assertTrue(map.isUsable)
    }

}