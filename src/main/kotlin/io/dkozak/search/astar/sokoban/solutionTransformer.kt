package io.dkozak.search.astar.sokoban


/**
 * up
 * down
 * left
 * right
 */
val input = "dlllluuuuRRdrUUUruLLLulDrrrdddlllddrUluRRdrUUUruLdlUruLLrrdddddllldddrrrruLdllUUUluRRdrUUUluRurDlddddlldddrruLdlUUUluRRdrUUUluurrdLulD"

fun main(args: Array<String>) {
    val orders = createOrders(input)
    orders.forEach(::println)
}

typealias Order = String

private enum class Directions {
    LEFT,
    RIGHT,
    UP,
    DOWN;
}

fun createOrders(directions: String): List<Order> {
    val orders = mutableListOf<Order>()
    var currentDirection = Directions.DOWN
    for (elem in directions) {
        val fullRotation = "fullRotation()"
        val left = "turnLeft()"
        val right = "turnRight()"
        val forward = "moveForward()"
        when (elem) {
            'u', 'U' -> {
                when (currentDirection) {
                    Directions.LEFT -> orders.add(right)
                    Directions.RIGHT -> orders.add(left)
                    Directions.DOWN -> orders.addAll(listOf(fullRotation, forward))
                    Directions.UP -> orders.add(forward)
                }
                currentDirection = Directions.UP

            }
            'd', 'D' -> {
                when (currentDirection) {
                    Directions.LEFT -> orders.add(left)
                    Directions.RIGHT -> orders.add(right)
                    Directions.DOWN -> orders.add(forward)
                    Directions.UP -> orders.addAll(listOf(fullRotation, forward))
                }
                currentDirection = Directions.DOWN
            }
            'l', 'L' -> {
                when (currentDirection) {
                    Directions.LEFT -> orders.add(forward)
                    Directions.RIGHT -> orders.addAll(listOf(fullRotation, forward))
                    Directions.DOWN -> orders.add(right)
                    Directions.UP -> orders.add(left)
                }
                currentDirection = Directions.LEFT
            }
            'r', 'R' -> {
                when (currentDirection) {
                    Directions.LEFT -> orders.addAll(listOf(fullRotation, forward))
                    Directions.RIGHT -> orders.add(forward)
                    Directions.DOWN -> orders.add(left)
                    Directions.UP -> orders.add(right)
                }
                currentDirection = Directions.RIGHT
            }

        }
    }
    return orders
}