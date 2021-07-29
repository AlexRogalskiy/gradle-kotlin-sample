package io.nullables.api.playground.algorithms

interface Graph {
    val V: Int
    var E: Int
    fun adjacentVertices(from: Int): Collection<Int>

    fun vertices(): IntRange = 0 until V
}
