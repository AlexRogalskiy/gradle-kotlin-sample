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

/**
 * Kruskal's algorithm will grow a solution from the cheapest edge by adding the next cheapest edge,
 * provided that it doesn't create a cycle.
 */
class KruskalMST(G: UWGraph) : MST {
    var weight: Double = 0.0
    var edges: Queue<UWGraph.Edge> = Queue()

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    init {
        val pq = PriorityQueue<UWGraph.Edge>(G.V, compareBy({ it.weight }))
        for (v in G.vertices()) {
            for (e in G.adjacentEdges(v)) {
                pq.add(e)
            }
        }

        val set = DisjointSet(G.V)
        while (!pq.isEmpty()) {
            val edge = pq.poll()
            if (!set.connected(edge.v, edge.w)) {
                edges.add(edge)
                set.union(edge.v, edge.w)
                weight += edge.weight
            }
        }
    }

    override fun edges(): Iterable<UWGraph.Edge> {
        return edges
    }

    override fun weight(): Double {
        return weight
    }
}
