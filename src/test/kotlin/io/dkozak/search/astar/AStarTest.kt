package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.parseMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AStarTest {

    @Test
    fun simpleParsing() {
        val serializedMap = """
                        07 12 04
                        XXXXXXX
                        XG....X
                        X.....X
                        XGXG.GX
                        XXXX.XX
                        X.J...X
                        X.J...X
                        X..XXXX
                        X.J.JMX
                        X.....X
                        X...XXX
                        XXXXX  """.trimIndent()
        val map = parseMap(serializedMap)

        val expected = """
                        XXXXXXX
                        XG....X
                        X.....X
                        XGXG.GX
                        XXXX.XX
                        X.J...X
                        X.J...X
                        X..XXXX
                        X.J.JMX
                        X.....X
                        X...XXX
                        XXXXXXX

        """.trimIndent()
        assertEquals(expected, map.toString())
    }

    @Test
    fun testPositions() {
        val serializedMap = """
                        07 12 04
                        XXXXXXX
                        XG....X
                        X.....X
                        XGXG.GX
                        XXXX.XX
                        X.J...X
                        X.J...X
                        X..XXXX
                        X.J.JMX
                        X.....X
                        X...XXX
                        XXXXX  """.trimIndent()
        val map = parseMap(serializedMap)
        assertEquals(8 to 5, map.sokobanMap__Dynamic.playerPosition)
        assertEquals(listOf(5 to 2, 6 to 2, 8 to 2, 8 to 4), map.sokobanMap__Dynamic.canPositions)
        assertEquals(listOf(1 to 1, 3 to 1, 3 to 3, 3 to 5), map.sokobanMap__Static.goals)
    }
}