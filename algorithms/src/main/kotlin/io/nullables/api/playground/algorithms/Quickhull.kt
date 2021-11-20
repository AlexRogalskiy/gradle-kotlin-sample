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

class Quickhull : ConvexHullAlgorithm {
    override fun convexHull(points: Array<Point>): Collection<Point> {
        if (points.size < 3) throw IllegalArgumentException("there must be at least 3 points")
        val left = points.min()!!
        val right = points.max()!!
        return this.quickHull(points.asList(), left, right) + quickHull(points.asList(), right, left)
    }

    private fun quickHull(
        points: Collection<Point>,
        first: Point,
        second: Point
    ): Collection<Point> {
        val pointsLeftOfLine = points
            .filter { it.isLeftOfLine(first, second) }
            .map { Pair(it, it.distanceToLine(first, second)) }
        if (pointsLeftOfLine.isEmpty()) {
            return listOf(second)
        } else {
            val max = pointsLeftOfLine.maxBy { it.second }!!.first
            val newPoints = pointsLeftOfLine.map { it.first }
            return quickHull(newPoints, first, max) + quickHull(newPoints, max, second)
        }
    }
}
