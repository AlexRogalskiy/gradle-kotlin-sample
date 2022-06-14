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

class Tree(var value: Int) {
    private val children: MutableList<Tree> = mutableListOf()

    fun size(): Int {
        return children.fold(1, { size, child -> size + child.size() })
    }

    fun height(): Int {
        return 1 + (children.map { it.size() }.max() ?: 0)
    }

    fun add(value: Int) {
        children.add(Tree(value))
    }
}
