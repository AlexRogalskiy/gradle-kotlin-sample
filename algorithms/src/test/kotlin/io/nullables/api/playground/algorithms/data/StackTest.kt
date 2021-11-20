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

import io.nullables.api.playground.algorithms.Stack
import org.junit.Assert
import org.junit.Test

class StackTest {
    @Test
    fun emptyTest() {
        val stack = Stack<Int>()
        Assert.assertEquals(0, stack.size)
        Assert.assertTrue(stack.isEmpty())
    }

    @Test(expected = NoSuchElementException::class)
    fun exceptionTest() {
        val stack = Stack<Int>()
        stack.peek()
    }

    @Test
    fun naiveTest() {
        val stack = Stack<Int>()
        for (i in 0..10) {
            stack.push(i)
        }
        for (i in 10 downTo 0) {
            Assert.assertEquals(i, stack.peek())
            Assert.assertEquals(i, stack.poll())
        }
        Assert.assertEquals(0, stack.size)
    }

    @Test
    fun naiveIteratorTest() {
        val stack = Stack<Int>()
        for (i in 0..10) {
            stack.push(i)
        }

        var k = 10
        for (i in stack) {
            Assert.assertEquals(i, k--)
        }
    }

    @Test
    fun naiveContainsTest() {
        val stack = Stack<Int>()
        for (i in 0..10) {
            stack.push(i)
        }

        for (i in 0..10) {
            Assert.assertTrue(stack.contains(i))
        }

        Assert.assertFalse(stack.contains(100))
        Assert.assertFalse(stack.contains(101))
        Assert.assertFalse(stack.contains(103))
    }
}
