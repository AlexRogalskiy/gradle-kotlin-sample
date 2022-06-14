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

class BinaryTree(var value: Int, var left: BinaryTree?, var right: BinaryTree?) {
    constructor(value: Int) : this(value, null, null)

    fun size(): Int {
        var size = 1
        if (left != null) {
            size += left!!.size()
        }
        if (right != null) {
            size += right!!.size()
        }
        return size
    }

    fun height(): Int {
        val left = if (left == null) 0 else left!!.height()
        val right = if (right == null) 0 else right!!.height()
        return maxOf(left, right) + 1
    }

    fun add(value: Int) {
        // adds the on the first empty level
        val queue = Queue<BinaryTree>()
        queue.add(this)
        while (!queue.isEmpty()) {
            val x = queue.poll()
            if (x.left == null) {
                x.left = BinaryTree(value)
                return
            } else if (x.right == null) {
                x.right = BinaryTree(value)
                return
            } else {
                queue.add(x.left!!)
                queue.add(x.right!!)
            }
        }
    }
}
