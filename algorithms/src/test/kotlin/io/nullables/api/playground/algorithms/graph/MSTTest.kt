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
package io.nullables.api.playground.algorithms.graph

import io.nullables.api.playground.algorithms.MST
import io.nullables.api.playground.algorithms.UWGraph
import org.junit.Assert
import org.junit.Test
import java.net.URL
import java.util.*

abstract class MSTTest(val strategy: (UWGraph) -> (MST)) {
    @Test
    fun test1() {
        val graph = UWGraph(4)
        graph.addEdge(0, 1, 4.0)
        graph.addEdge(1, 2, 1.0)
        graph.addEdge(2, 3, 2.0)
        graph.addEdge(0, 3, 5.0)
        graph.addEdge(0, 1, 4.0)
        graph.addEdge(1, 3, 3.0)
        val mst = strategy(graph)
        Assert.assertEquals(7.0, mst.weight(), 1e-12)
    }

    @Test
    fun test2() {
        val graph = UWGraph(5)
        graph.addEdge(0, 1, 4.0)
        graph.addEdge(1, 2, 1.0)
        graph.addEdge(2, 3, 7.0)
        graph.addEdge(3, 4, 6.0)
        graph.addEdge(0, 4, 2.0)
        graph.addEdge(1, 4, 3.0)
        graph.addEdge(1, 3, 5.0)
        val mst = strategy(graph)
        Assert.assertEquals(11.0, mst.weight(), 1e-12)
    }

    @Test
    fun test3() {
        val graph = readFromURL(URL("https://algs4.cs.princeton.edu/43mst/tinyEWG.txt"))
        val mst = strategy(graph)
        Assert.assertEquals(1.81, mst.weight(), 1e-12)
    }

    @Test
    fun test4() {
        val graph = readFromURL(URL("https://algs4.cs.princeton.edu/43mst/mediumEWG.txt"))
        val mst = strategy(graph)
        Assert.assertEquals(10.46351, mst.weight(), 1e-12)
    }

    @Test
    fun test5() {
        val graph = readFromURL(URL("https://algs4.cs.princeton.edu/43mst/1000EWG.txt"))
        val mst = strategy(graph)
        Assert.assertEquals(20.77320, mst.weight(), 1e-12)
    }

    @Test
    fun test6() {
        val graph = readFromURL(URL("https://algs4.cs.princeton.edu/43mst/10000EWG.txt"))
        val mst = strategy(graph)
        Assert.assertEquals(65.24072, mst.weight(), 1e-12)
    }

    fun readFromURL(url: URL): UWGraph {
        Scanner(url.openStream()).useLocale(Locale.US).use { scanner ->
            val graph = UWGraph(scanner.nextInt())
            for (i in 0 until scanner.nextInt()) {
                graph.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble())
            }
            scanner.close()
            return graph
        }
    }
}
