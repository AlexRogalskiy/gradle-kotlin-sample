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

import io.nullables.api.playground.algorithms.PriorityQueue
import org.junit.Assert
import org.junit.Test

class PriorityQueueTest {
    @Test
    fun emptyTest() {
        val pq = PriorityQueue<Int>(3)
        Assert.assertEquals(0, pq.size)
        Assert.assertTrue(pq.isEmpty())
    }

    @Test(expected = NoSuchElementException::class)
    fun exceptionTest() {
        val pq = PriorityQueue<Int>(3)
        pq.peek()
    }

    @Test
    fun naiveTest() {
        val pq = PriorityQueue<Int>(3)
        for (i in 10 downTo 1) {
            pq.add(i)
            Assert.assertEquals(i, pq.peek())
        }
        for (i in 1..10) {
            Assert.assertEquals(i, pq.poll())
        }
    }
}
