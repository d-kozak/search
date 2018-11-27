package io.dkozak.search.astar

import io.dkozak.search.astar.sokoban.Direction
import io.dkozak.search.astar.sokoban.SokobanNode
import io.dkozak.search.astar.sokoban.parseMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class AStartSearchTest {

    //    @Test
    fun competitionMap() {
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
        val state = SokobanNode(map)
        val result = search(state)

        result.forEach(::println)

        val directions = result.map { it as SokobanNode }.map { it.direction }
        println(directions)

        File("solutions.dat").bufferedWriter().use {
            it.write(directions.toString())
        }
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

        val directions = result.map { it as SokobanNode }.map { it.direction }
        println(directions)
        assertEquals(listOf(Direction.DUMMY, Direction.RIGHT), directions)
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


        val directions = result.map { it as SokobanNode }.map { it.direction }
        println(directions)

        assertEquals(listOf(Direction.DUMMY, Direction.RIGHT, Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT, Direction.LEFT, Direction.DOWN), directions)

    }

}