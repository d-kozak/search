package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.SokobanNode
import io.dkozak.search.astar.sokoban.parseMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AStartSearchTest {

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
        val result = search(state)
        assertEquals(2, result.size)
        assertEquals("""
            XXXXX
            XMJGX
            XXXXX
            XXXXX

        """.trimIndent(), result[0].toString())
        assertEquals("""
            XXXXX
            X.MJX
            XXXXX
            XXXXX

        """.trimIndent(), result[1].toString())
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
        val result = search(state)

        result.forEach(::println)
        assertEquals(4, result.size)
    }


    @Test
    fun littleMoreComplex2() {
        val serializedMap = """
                        04 04 01
                        XXXXX
                        X...X
                        XMJ.X
                        XJXGX
                        XGXXX
                        XXXXX
                        """.trimIndent()
        val map = parseMap(serializedMap)
        val state = SokobanNode(map)
        val result = search(state)

        result.forEach(::println)
        assertEquals(8, result.size)
    }

}