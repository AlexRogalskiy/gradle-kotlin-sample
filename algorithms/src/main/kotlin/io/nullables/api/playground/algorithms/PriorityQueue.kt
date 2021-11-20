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

class PriorityQueue<T>(size: Int, val comparator: Comparator<T>? = null) : Collection<T> {
    public override var size: Int = 0
        private set
    private var arr: Array<T?> = Array<Comparable<T>?>(size, { null }) as Array<T?>

    fun add(element: T) {
        if (size + 1 == arr.size) {
            resize()
        }
        arr[++size] = element
        swim(size)
    }

    fun peek(): T {
        if (size == 0) throw NoSuchElementException()
        return arr[1]!!
    }

    fun poll(): T {
        if (size == 0) throw NoSuchElementException()
        val res = peek()
        arr.exch(1, size--)
        sink(1)
        arr[size + 1] = null
        if ((size > 0) && (size == (arr.size - 1) / 4)) {
            resize()
        }
        return res
    }

    private fun swim(n: Int) {
        Companion.swim(arr, n, comparator)
    }

    private fun sink(n: Int) {
        Companion.sink(arr, n, size, comparator)
    }

    private fun resize() {
        val old = arr
        arr = Array<Comparable<T>?>(size * 2, { null }) as Array<T?>
        System.arraycopy(old, 0, arr, 0, size + 1)
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun contains(element: T): Boolean {
        for (obj in this) {
            if (obj == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun iterator(): Iterator<T> {
        return arr.copyOfRange(1, size + 1).map { it!! }.iterator()
    }

    companion object {
        private fun <T> greater(
            arr: Array<T?>,
            i: Int,
            j: Int,
            comparator: Comparator<T>? = null
        ): Boolean {
            if (comparator != null) {
                return comparator.compare(arr[i], arr[j]) > 0
            } else {
                val left = arr[i]!! as Comparable<T>
                return left > arr[j]!!
            }
        }

        fun <T> sink(arr: Array<T?>, a: Int, size: Int, comparator: Comparator<T>? = null) {
            var k = a
            while (2 * k <= size) {
                var j = 2 * k
                if (j < size && greater(arr, j, j + 1, comparator)) j++
                if (!greater(arr, k, j, comparator)) break
                arr.exch(k, j)
                k = j
            }
        }

        fun <T> swim(arr: Array<T?>, size: Int, comparator: Comparator<T>? = null) {
            var n = size
            while (n > 1 && greater(arr, n / 2, n, comparator)) {
                arr.exch(n, n / 2)
                n /= 2
            }
        }
    }
}
