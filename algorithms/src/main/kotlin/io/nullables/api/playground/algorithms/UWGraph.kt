package io.nullables.api.playground.algorithms

class UWGraph(public override val V: Int) : Graph {
    override var E: Int = 0
    private val adj: Array<Queue<Edge>> = Array(V) { Queue() }

    class Edge(val v: Int, val w: Int, val weight: Double) :
        Comparable<Edge> {
        override fun compareTo(other: Edge): Int {
            return this.weight.compareTo(other.weight)
        }

        fun other(s: Int): Int {
            if (s == v) return w
            if (s == w) return v
            throw IllegalArgumentException()
        }
    }

    fun addEdge(v: Int, w: Int, weight: Double) {
        val edge = Edge(v, w, weight)
        adj[v].add(edge)
        adj[w].add(edge)
        E++
    }

    fun adjacentEdges(v: Int): Collection<Edge> {
        return adj[v]
    }

    override fun adjacentVertices(from: Int): Collection<Int> {
        return adjacentEdges(from).map { it.other(from) }
    }

    fun degree(v: Int): Int {
        return adj[v].size
    }

    fun edges(): Collection<Edge> {
        return adj.flatMap { it }
    }
}
