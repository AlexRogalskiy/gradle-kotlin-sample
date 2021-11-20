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

class BoruvkaMST(G: UWGraph) : MST {
    var weight: Double = 0.0
    var edges: Queue<UWGraph.Edge> = Queue()

    init {
        val uf = DisjointSet(G.V)

        // repeat at most log V times or until we have V-1 edges
        var t = 1
        while (t < G.V && edges.size < G.V - 1) {

            // foreach tree in forest, find closest edge
            // if edge weights are equal, ties are broken in favor of first edge in G.edges()
            val closest = arrayOfNulls<UWGraph.Edge>(G.V)
            for (e in G.edges()) {
                val v = e.v
                val w = e.w
                val i = uf.find(v)
                val j = uf.find(w)
                if (i == j) continue   // same tree
                if (closest[i] == null || e < closest[i]!!) closest[i] = e
                if (closest[j] == null || e < closest[j]!!) closest[j] = e
            }

            // add newly discovered edges to MST
            for (i in 0 until G.V) {
                val e = closest[i]
                if (e != null) {
                    val v = e.v
                    val w = e.w
                    // don't add the same edge twice
                    if (!uf.connected(v, w)) {
                        edges.add(e)
                        weight += e.weight
                        uf.union(v, w)
                    }
                }
            }
            t += t
        }
    }

    override fun edges(): Iterable<UWGraph.Edge> {
        return edges
    }

    override fun weight(): Double {
        return weight
    }
}
