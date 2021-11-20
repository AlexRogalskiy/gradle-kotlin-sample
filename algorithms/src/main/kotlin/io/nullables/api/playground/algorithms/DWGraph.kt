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

class DWGraph(public override val V: Int) : Graph {
    public override var E: Int = 0
    private val adj: Array<Queue<Edge>> = Array(V) { Queue<Edge>() }
    private val indegree: IntArray = IntArray(V)

    public class Edge(public val from: Int, public val to: Int, public val weight: Double)

    public fun addEdge(from: Int, to: Int, weight: Double) {
        val edge = Edge(from, to, weight)
        adj[from].add(edge)
        indegree[to]++
        E++
    }

    public fun edges(): Collection<Edge> {
        val stack = Stack<Edge>()
        adj.flatMap { it }.forEach { stack.push(it) }
        return stack
    }

    public fun adjacentEdges(from: Int): Collection<Edge> {
        return adj[from]
    }

    public override fun adjacentVertices(from: Int): Collection<Int> {
        return adjacentEdges(from).map { it.to }
    }

    public fun outdegree(v: Int): Int {
        return adj[v].size
    }
}
