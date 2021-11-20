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

import io.nullables.api.playground.algorithms.Point
import io.nullables.api.playground.algorithms.Rect
import io.nullables.api.playground.algorithms.quadTreeOf
import org.junit.Assert
import org.junit.Test

class QuadTreeTest {
    @Test
    fun naiveTest1() {
        val qt = quadTreeOf(
            Rect(0, 0, 100, 100),
            Point(5, 20) to "Foo",
            Point(50, 32) to "Bar",
            Point(47, 96) to "Baz",
            Point(50, 50) to "Bing",
            Point(12, 0) to "Bong"
        )

        val points1 = qt[Rect(4, 0, 51, 98)].sorted()
        Assert.assertEquals(listOf("Bar", "Baz", "Bing", "Bong", "Foo"), points1)

        val points2 = qt[Rect(5, 0, 50, 96)].sorted()
        Assert.assertEquals(listOf("Bar", "Baz", "Bing", "Bong", "Foo"), points2)

        val points3 = qt[Rect(55, 0, 50, 96)]
        Assert.assertEquals(0, points3.count())

        val points4 = qt[Rect(4, 19, 6, 21)].sorted()
        Assert.assertEquals(listOf("Foo"), points4)
    }
}
