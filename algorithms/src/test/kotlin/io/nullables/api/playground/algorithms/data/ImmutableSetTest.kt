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
package io.nullables.api.playground.algorithms.data

import io.nullables.api.playground.algorithms.immutableSetOf
import org.junit.Assert
import org.junit.Test

class ImmutableSetTest {
    @Test
    fun test1() {
        Assert.assertFalse(immutableSetOf(3).contains(1))
        Assert.assertFalse(immutableSetOf(3).contains(2))
        Assert.assertFalse(immutableSetOf(3).contains(4))
        Assert.assertFalse(immutableSetOf(3).contains(5))
        Assert.assertTrue(immutableSetOf(3).contains(3))
    }

    @Test
    fun test2() {
        val set = immutableSetOf(*(10 downTo 1).toList().toTypedArray())
        for (v in set) {
            Assert.assertTrue(set.contains(v))
        }
        Assert.assertEquals(10, set.size)
        Assert.assertFalse(set.isEmpty())
        Assert.assertFalse(set.contains(42))
        Assert.assertFalse(set.contains(-42))
    }

    @Test
    fun test3() {
        val set = immutableSetOf(*(0..100).toList().toTypedArray())
        for (v in -100..-1) {
            Assert.assertFalse(set.contains(v))
        }
        for (v in 0..100) {
            Assert.assertTrue(set.contains(v))
        }
        for (v in 101..200) {
            Assert.assertFalse(set.contains(v))
        }
    }
}
