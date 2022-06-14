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

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int {
        if (x == other.x) return y.compareTo(other.y)
        return x.compareTo(other.x)
    }

    fun isLeftOfLine(from: Point, to: Point): Boolean {
        return crossProduct(from, to) > 0
    }

    fun crossProduct(origin: Point, p2: Point): Int {
        return (p2.x - origin.x) * (this.y - origin.y) - (p2.y - origin.y) * (this.x - origin.x)
    }

    fun distanceToLine(a: Point, b: Point): Double {
        return Math.abs((b.x - a.x) * (a.y - this.y) - (a.x - this.x) * (b.y - a.y)) /
                Math.sqrt(Math.pow((b.x - a.x).toDouble(), 2.0) + Math.pow((b.y - a.y).toDouble(), 2.0))
    }

    fun euclideanDistanceTo(that: Point): Double {
        return EUCLIDEAN_DISTANCE_FUNC(this, that)
    }

    fun manhattanDistanceTo(that: Point): Double {
        return MANHATTAN_DISTANCE_FUNC(this, that)
    }

    companion object {
        // < 0 : Counterclockwise
        // = 0 : p, q and r are colinear
        // > 0 : Clockwise
        fun orientation(p: Point, q: Point, r: Point): Int {
            return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y)
        }

        val EUCLIDEAN_DISTANCE_FUNC: (Point, Point) -> (Double) = { p, q ->
            val dx = p.x - q.x
            val dy = p.y - q.y
            Math.sqrt((dx * dx + dy * dy).toDouble())
        }

        val MANHATTAN_DISTANCE_FUNC: (Point, Point) -> (Double) = { p, q ->
            val dx = p.x - q.x
            val dy = p.y - q.y
            Math.sqrt((dx * dx + dy * dy).toDouble())
        }
    }
}
