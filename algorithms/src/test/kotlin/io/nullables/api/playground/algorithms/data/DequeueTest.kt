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

import io.nullables.api.playground.algorithms.Dequeue
import org.junit.Assert
import org.junit.Test

class DequeueTest {
    @Test
    fun emptyTest() {
        val dequeue = Dequeue<Int>()
        Assert.assertEquals(0, dequeue.size)
        Assert.assertTrue(dequeue.isEmpty())
    }

    @Test(expected = NoSuchElementException::class)
    fun exceptionTest() {
        val dequeue = Dequeue<Int>()
        dequeue.peekFirst()
    }

    @Test
    fun naiveTest() {
        val dequeue = Dequeue<Int>()
        for (i in 0..10) {
            dequeue.add(i)
        }
        for (i in 0..5) {
            Assert.assertEquals(i, dequeue.peekFirst())
            Assert.assertEquals(i, dequeue.pollFirst())
        }
        for (i in 10..6) {
            Assert.assertEquals(i, dequeue.peekLast())
            Assert.assertEquals(i, dequeue.pollLast())
        }
    }

    @Test
    fun naiveIteratorTest() {
        val dequeue = Dequeue<Int>()
        for (i in 0..10) {
            dequeue.add(i)
        }

        var k = 0
        for (i in dequeue) {
            Assert.assertEquals(i, k++)
        }
    }

    @Test
    fun naiveContainsTest() {
        val dequeue = Dequeue<Int>()
        for (i in 0..10) {
            dequeue.add(i)
        }

        for (i in 0..10) {
            Assert.assertTrue(dequeue.contains(i))
        }

        Assert.assertFalse(dequeue.contains(100))
        Assert.assertFalse(dequeue.contains(101))
        Assert.assertFalse(dequeue.contains(103))
    }
}
