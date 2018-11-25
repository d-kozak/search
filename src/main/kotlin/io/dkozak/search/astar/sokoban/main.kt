package io.dkozak.search.astar.sokoban


fun main(args: Array<String>) {
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
    println(map)
}
