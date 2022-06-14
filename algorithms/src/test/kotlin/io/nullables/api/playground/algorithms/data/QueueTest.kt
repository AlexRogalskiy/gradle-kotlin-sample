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

import io.nullables.api.playground.algorithms.Queue
import org.junit.Assert
import org.junit.Test

class QueueTest {
    @Test
    fun emptyTest() {
        val queue = Queue<Int>()
        Assert.assertEquals(0, queue.size)
        Assert.assertTrue(queue.isEmpty())
    }

    @Test(expected = NoSuchElementException::class)
    fun exceptionTest() {
        val queue = Queue<Int>()
        queue.peek()
    }

    @Test
    fun naiveTest() {
        val queue = Queue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }
        for (i in 0..10) {
            Assert.assertEquals(i, queue.peek())
            Assert.assertEquals(i, queue.poll())
        }
        Assert.assertEquals(0, queue.size)
    }

    @Test
    fun naiveIteratorTest() {
        val queue = Queue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }

        var k = 0
        for (i in queue) {
            Assert.assertEquals(i, k++)
        }
    }

    @Test
    fun naiveContainsTest() {
        val queue = Queue<Int>()
        for (i in 0..10) {
            queue.add(i)
        }

        for (i in 0..10) {
            Assert.assertTrue(queue.contains(i))
        }

        Assert.assertFalse(queue.contains(100))
        Assert.assertFalse(queue.contains(101))
        Assert.assertFalse(queue.contains(103))
    }
}
