package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.*
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
        assertEquals(0, map.playerToNearestCanDistance())
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
        assertEquals(0, map.playerToNearestCanDistance())
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
        assertEquals(0, map.playerToNearestCanDistance())
        assertTrue(map.isUsable)
    }


    @Test
    fun `littleMoreComplex, Player is far away from cans`() {
        val serializedMap = """
                        04 04 01
                        XXXXXX
                        XM...X
                        X.J..X
                        XJX.GX
                        XGXXXX
                        XXXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        assertEquals(5, map.heuristic)
        assertEquals(1, map.playerToNearestCanDistance())
        assertTrue(map.isUsable)
    }

    @Test
    fun `littleMoreComplex, Player is even further away from cans`() {
        val serializedMap = """
                        04 04 01
                        XXXXXXX
                        XM....X
                        X..J..X
                        X.JX.GX
                        X.GXXXX
                        X.XXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        assertEquals(2, map.playerToNearestCanDistance())
        assertEquals(6, map.heuristic)
        assertTrue(map.isUsable)
    }

    @Test
    fun `littleMoreComplex, Player is even more further away from cans`() {
        val serializedMap = """
                        04 04 01
                        M.XXXXX
                        X.....X
                        X..J..X
                        X.JX.GX
                        X.GXXXX
                        X.XXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        assertEquals(4, map.playerToNearestCanDistance())
        assertEquals(8, map.heuristic)
        assertTrue(map.isUsable)
    }

    @Test
    fun `heuristicsTest `() {
        // 2 5
        // this is also an invalid state, beucase we cannot move the can to the goal anymore
        val serializedMap = """
        01 01 01
        XXXXXXX
        X..XG.X
        X...MJX
        XJ....X
        XG....X
        XXXXXXX
        """.trimIndent()
        val map = parseMap(serializedMap)
        val node = SokobanNode(map)
    }



}