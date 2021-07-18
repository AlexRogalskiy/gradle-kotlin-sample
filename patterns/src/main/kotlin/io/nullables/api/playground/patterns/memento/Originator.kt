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
package io.nullables.api.playground.patterns.memento

/**
 * The Originator is the class that creates the memento. This class is also responsible for restoring the state from a given memento.
 */

class Originator {
    class Memento<T>(val state: T) {
        init {
            println("Creating memento with state $state")
        }
    }

    var state = ""
        private set(v) {
            println("Originator setting state to $v")
            field = v
        }

    fun update(newState: String) {
        state = newState
    }

    fun restore(memento: Memento<String>) {
        println("Restoring state to ${memento.state}")
        state = memento.state
    }

    fun save() = Memento(state)
}

fun main(args: Array<String>) {
    val states = mutableListOf<Originator.Memento<String>>()

    val originator = Originator()

    originator.update("State 1")
    originator.update("State 2")
    states.add(originator.save())

    originator.update("State 3")
    states.add(originator.save())

    originator.update("State 4")

    originator.restore(states[1])
}
