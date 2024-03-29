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

class GrahamScan : ConvexHullAlgorithm {
    override fun convexHull(points: Array<Point>): Collection<Point> {
        if (points.size < 3) throw IllegalArgumentException("there must be at least 3 points")

        val hull = Stack<Point>()

        // Find the leftmost point
        points.sortBy { it.y }

        // Sort points by polar angle with p
        points.sortWith(Comparator { q1, q2 ->
            val dx1 = q1.x - points[0].x
            val dy1 = q1.y - points[0].y
            val dx2 = q2.x - points[0].x
            val dy2 = q2.y - points[0].y

            if (dy1 >= 0 && dy2 < 0)
                -1    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0)
                +1    // q1 below; q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if (dx1 >= 0 && dx2 < 0)
                    -1
                else if (dx2 >= 0 && dx1 < 0)
                    +1
                else
                    0
            } else
                -Point.orientation(points[0], q1, q2)     // both above or below
            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        })

        hull.push(points[0])
        hull.push(points[1])

        for (i in IntRange(2, points.size - 1)) {
            var top = hull.poll()
            while (Point.orientation(hull.peek(), top, points[i]) <= 0) {
                top = hull.poll()
            }
            hull.push(top)
            hull.push(points[i])
        }

        return hull
    }
}
