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
package io.nullables.api.playground.algorithms.search

import io.nullables.api.playground.algorithms.AbstractSearchStrategy
import org.junit.Assert
import org.junit.Test

abstract class AbstractSearchTest<out T : AbstractSearchStrategy<Int>>(private val strategy: T) {
    @Test
    fun emptyTest() {
        Assert.assertEquals(-1, strategy.perform(emptyArray(), 1))
    }

    @Test
    fun singletonTest() {
        Assert.assertEquals(0, strategy.perform(arrayOf(1), 1))
        Assert.assertEquals(-1, strategy.perform(arrayOf(1), 2))
    }

    @Test
    fun twoElementsTest() {
        Assert.assertEquals(0, strategy.perform(arrayOf(1, 2), 1))
        Assert.assertEquals(1, strategy.perform(arrayOf(1, 2), 2))
        Assert.assertEquals(-1, strategy.perform(arrayOf(1, 2), 3))
    }

    @Test
    fun tenElementsTest() {
        Assert.assertEquals(0, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1))
        Assert.assertEquals(1, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 2))
        Assert.assertEquals(2, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3))
        Assert.assertEquals(3, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4))
        Assert.assertEquals(4, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5))
        Assert.assertEquals(5, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 6))
        Assert.assertEquals(6, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 7))
        Assert.assertEquals(7, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 8))
        Assert.assertEquals(8, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 9))
        Assert.assertEquals(9, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 10))
        Assert.assertEquals(-1, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 0))
        Assert.assertEquals(-1, strategy.perform(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 11))
    }
}
