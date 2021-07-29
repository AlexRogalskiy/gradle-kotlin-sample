package io.nullables.api.playground.algorithms

class UUGraph(override val V: Int) : Graph {
    override var E: Int = 0
    private val adj: Array<Queue<Int>> = Array(V) { Queue() }

    fun addEdge(v: Int, w: Int) {
        adj[v].add(w)
        adj[w].add(v)
        E++
    }

    override fun adjacentVertices(from: Int): Collection<Int> {
        return adj[from]
    }

    fun degree(v: Int): Int {
        return adj[v].size
    }
}
