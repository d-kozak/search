package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.SokobanNode
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


    @Test
    fun findNextStates1() {
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
        val children = SokobanNode(map).getChildren()
        val expected = listOf(
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X.J.J.X
                X....MX
                X...XXX
                XXXXXXX

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X.JJM.X
                X.....X
                X...XXX
                XXXXXXX

                """.trimIndent()
        )

        children.forEachIndexed { index, (node, _) ->
            assertEquals(expected[index], node.toString())
            println(node)
        }
    }

    @Test
    fun findNextStates2() {
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
                        X.J.J.X
                        X....MX
                        X...XXX
                        XXXXXXX""".trimIndent()
        val map = parseMap(serializedMap)
        val children = SokobanNode(map).getChildren()
        val expected = listOf(
                """
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

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X.J.J.X
                X...M.X
                X...XXX
                XXXXXXX

                """.trimIndent()
        )

        children.forEachIndexed { index, (node, _) ->
            assertEquals(expected[index], node.toString())
            println(node)
        }
    }

    @Test
    fun findNextStates3() {
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
                        X.JJM.X
                        X.....X
                        X...XXX
                        XXXXXXX""".trimIndent()
        val map = parseMap(serializedMap)
        val children = SokobanNode(map).getChildren()
        val expected = listOf(
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X.JJ..X
                X...M.X
                X...XXX
                XXXXXXX

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X.JJ.MX
                X.....X
                X...XXX
                XXXXXXX

                """.trimIndent()
        )

        children.forEachIndexed { index, (node, _) ->
            assertEquals(expected[index], node.toString())
            println(node)
        }
    }


    @Test
    fun findNextStates4() {
        val serializedMap = """
                        07 12 04
                        XXXXXXX
                        XG....X
                        X.....X
                        XGXG.GX
                        XXXX.XX
                        X.J...X
                        X.J...X
                        X.MXXXX
                        X.JJ..X
                        X.....X
                        X...XXX
                        XXXXXXX""".trimIndent()
        val map = parseMap(serializedMap)
        val children = SokobanNode(map).getChildren()
        val expected = listOf(
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X.MJ..X
                X.J...X
                X...XXX
                XXXXXXX

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                XM.XXXX
                X.JJ..X
                X.....X
                X...XXX
                XXXXXXX

                """.trimIndent()
        )

        children.forEachIndexed { index, (node, _) ->
            assertEquals(expected[index], node.toString())
            println(node)
        }

    }

    @Test
    fun findNextStates5() {
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
                        X.MJ..X
                        X.J...X
                        X...XXX
                        XXXXXXX""".trimIndent()
        val map = parseMap(serializedMap)
        val children = SokobanNode(map).getChildren()
        val expected = listOf(
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X.MXXXX
                X..J..X
                X.J...X
                X...XXX
                XXXXXXX

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X..J..X
                X.M...X
                X.J.XXX
                XXXXXXX

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                XM.J..X
                X.J...X
                X...XXX
                XXXXXXX

                """.trimIndent(),
                """
                XXXXXXX
                XG....X
                X.....X
                XGXG.GX
                XXXX.XX
                X.J...X
                X.J...X
                X..XXXX
                X..MJ.X
                X.J...X
                X...XXX
                XXXXXXX

                """.trimIndent()
        )

        children.forEachIndexed { index, (node, _) ->
            assertEquals(expected[index], node.toString())
            println(node)
        }

    }
}