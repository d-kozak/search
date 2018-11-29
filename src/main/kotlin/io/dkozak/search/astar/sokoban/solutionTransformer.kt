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
    var i = 0
    while (i < directions.length) {
        val elem = directions[i]
        val currentMoveWithoutCan = elem.isLowerCase()
        val lastMoveWithCan = if (i - 1 > 0) (directions[i - 1]).isUpperCase() else false
        val fullRotation = "full_rotation()"
        val left = "turn_left()"
        val right = "turn_right()"
        val forward = "move_forward()"

        if (currentMoveWithoutCan && lastMoveWithCan) {
            orders.addAll(listOf(forward, fullRotation))
            currentDirection = when (currentDirection) {
                Directions.UP -> Directions.DOWN
                Directions.DOWN -> Directions.UP
                Directions.LEFT -> Directions.RIGHT
                Directions.RIGHT -> Directions.LEFT
            }
        }

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
        i++
    }
    return orders
}