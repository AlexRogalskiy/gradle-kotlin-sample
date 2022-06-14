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

class SierpinskiTriangle {
    /**
     * @param d base of the triangle (i.e. the smallest dimension)
     * @param n fractalization depth (must be less than log2(d))
     * @throws IllegalArgumentException if n > log2(d)
     */
    fun makeTriangles(base: Int, n: Int): Array<BooleanArray> {
        if (n > log2(base)) throw IllegalArgumentException(
            "fractalization depth must be less than log2(base): " +
                "$n > ${log2(base).toInt()}"
        )
        val arr = Array(base, { BooleanArray(base * 2 - 1) })
        drawTriangles(n, arr, 0, 0, base - 1, base * 2 - 2)
        return arr
    }

    fun drawTriangles(
        n: Int,
        arr: Array<BooleanArray>,
        top: Int,
        left: Int,
        bottom: Int,
        right: Int
    ) {
        if (n > 0) {
            val width = right - left
            val height = bottom - top
            drawTriangles(
                n - 1, arr,
                top,
                left + width / 4 + 1,
                top + height / 2,
                right - width / 4 - 1
            )
            drawTriangles(
                n - 1, arr,
                top + 1 + height / 2,
                left,
                bottom,
                left + width / 2 - 1
            )
            drawTriangles(
                n - 1, arr,
                top + 1 + height / 2,
                left + width / 2 + 1,
                bottom,
                right
            )
        } else {
            drawTriangles(arr, top, left, bottom, right)
        }
    }

    fun drawTriangles(arr: Array<BooleanArray>, top: Int, left: Int, bottom: Int, right: Int) {
        val height = bottom - top
        val width = right - left
        for (i in 0..height) {
            for (j in (height - i)..width / 2) {
                arr[top + i][left + j] = true
            }
            for (j in (width / 2..width / 2 + i)) {
                arr[top + i][left + j] = true
            }
        }
    }
}

fun main(args: Array<String>) {
    SierpinskiTriangle()
        .makeTriangles(128, 7)
        .map { array ->
            array.map { if (it) 'x' else ' ' }.joinToString(separator = "")
        }
        .forEach { println(it) }
}
