/*
 * Copyright (C) 2021. Alexander Rogalskiy. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
