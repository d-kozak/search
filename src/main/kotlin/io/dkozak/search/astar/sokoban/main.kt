package io.dkozak.search.astar.sokoban


val input1 = """07 12 04
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
XXXXX  """

fun main(args: Array<String>) {
    val map = parseMap(input1)
    println(map)
}
