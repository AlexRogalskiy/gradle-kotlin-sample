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

/**
 * Developed by Tony Hoare in 1959, with his work published in 1961, Quicksort is an efficient sort algorithm using
 * divide and conquer approach. Quicksort first divides a large array into two smaller sub-arrays: the low elements
 * and the high elements. Quicksort can then recursively sort the sub-arrays. The steps are:
 * 1) Pick an element, called a pivot, from the array.
 * 2) Partitioning: reorder the array so that all elements with values less than the pivot come before the pivot,
 * while all elements with values greater than the pivot come after it (equal values can go either way).
 * After this partitioning, the pivot is in its final position. This is called the partition operation.
 * 3) Recursively apply the above steps to the sub-array of elements with smaller values and separately to
 * the sub-array of elements with greater values.
 */
@ComparisonSort
@UnstableSort
class QuickSort : AbstractSortStrategy() {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        sort(arr, 0, arr.size - 1)
    }

    private fun <T : Comparable<T>> sort(arr: Array<T>, lo: Int, hi: Int) {
        if (hi <= lo) return
        val j = partition(arr, lo, hi)
        sort(arr, lo, j - 1)
        sort(arr, j + 1, hi)
    }

    private fun <T : Comparable<T>> partition(arr: Array<T>, lo: Int, hi: Int): Int {
        var i = lo
        var j = hi + 1
        val v = arr[lo]
        while (true) {
            while (arr[++i] < v) {
                if (i == hi) break
            }
            while (v < arr[--j]) {
                if (j == lo) break
            }
            if (j <= i) break
            arr.exch(j, i)
        }
        arr.exch(j, lo)
        return j
    }
}
