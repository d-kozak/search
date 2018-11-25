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
}