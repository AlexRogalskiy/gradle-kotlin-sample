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
package io.nullables.api.playground.algorithms.geometry

import io.nullables.api.playground.algorithms.ConvexHullAlgorithm
import io.nullables.api.playground.algorithms.Point
import org.junit.Assert
import org.junit.Test

abstract class ConvexHullTest(private val strategy: ConvexHullAlgorithm) {
    @Test
    fun test1() {
        val points = arrayOf(
            Point(1, 1),
            Point(2, 5),
            Point(3, 3),
            Point(5, 3),
            Point(3, 2),
            Point(2, 2)
        )
        val expected = sortedSetOf(
            Point(1, 1),
            Point(2, 5),
            Point(5, 3)
        )
        val hull = strategy.convexHull(points)
        Assert.assertEquals(expected, hull.toSortedSet())
    }

    @Test
    fun test2() {
        val points = arrayOf(
            Point(3, 2),
            Point(2, 5),
            Point(4, 5)
        )
        val expected = points.toSortedSet()
        val hull = strategy.convexHull(points)
        Assert.assertEquals(expected, hull.toSortedSet())
    }

    @Test
    fun test3() {
        val points = arrayOf(
            Point(0, 3),
            Point(2, 2),
            Point(1, 1),
            Point(2, 1),
            Point(3, 0),
            Point(0, 0),
            Point(3, 3)
        )
        val expected = sortedSetOf(
            Point(0, 3),
            Point(3, 0),
            Point(0, 0),
            Point(3, 3)
        )
        val hull = strategy.convexHull(points)
        Assert.assertEquals(expected, hull.toSortedSet())
    }

    @Test
    fun test4() {
        val expected = arrayOf(
            Point(10, 3),
            Point(8, 0),
            Point(2, 0),
            Point(0, 3),
            Point(2, 6),
            Point(8, 6)
        )
        val points = expected + arrayOf(
            Point(3, 5),
            Point(3, 3),
            Point(6, 3),
            Point(6, 3),
            Point(7, 1),
            Point(8, 4)
        )
        val hull = strategy.convexHull(points)
        Assert.assertEquals(expected.toSortedSet(), hull.toSortedSet())
    }

    @Test(expected = IllegalArgumentException::class)
    fun exceptionTest0() {
        strategy.convexHull(emptyArray())
    }

    @Test(expected = IllegalArgumentException::class)
    fun exceptionTest1() {
        strategy.convexHull(arrayOf(Point(0, 0)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun exceptionTest2() {
        strategy.convexHull(arrayOf(Point(0, 0), Point(1, 1)))
    }
}
