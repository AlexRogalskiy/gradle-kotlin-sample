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
 * Heap sort is a comparison based sorting technique based on Binary Heap data structure. It is similar to selection
 * sort where we first find the maximum element and place the maximum element at the end. We repeat the same process
 * for remaining element.
 */
@ComparisonSort
@UnstableSort
class HeapSort : AbstractSortStrategy() {
    override fun <T : Comparable<T>> perform(arr: Array<T>) {
        for (k in arr.size / 2 downTo 1) {
            PriorityQueue.sink(arr as Array<T?>, k - 1, arr.size - 1, reverseOrder<T>())
        }
        for (k in arr.size downTo 1) {
            arr.exch(0, k - 1)
            PriorityQueue.sink(arr as Array<T?>, 0, k - 2, reverseOrder<T>())
        }
    }
}
