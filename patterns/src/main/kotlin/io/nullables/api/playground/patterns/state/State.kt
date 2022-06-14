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
package io.nullables.api.playground.patterns.state

sealed class State

class ConcreteStateA() : State() {
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}

object ConcreteStateB : State()

class Context {
    private var state: State = ConcreteStateA()

    fun setStateB() {
        state = ConcreteStateB
    }

    fun setStateA() {
        state = ConcreteStateA()
    }

    val stateText: String
        get() = when (state) {
            is ConcreteStateA -> "State A"
            is ConcreteStateB -> "State B"
        }

    override fun toString() = "State: $stateText"
}

fun main(args: Array<String>) {
    val context = Context()
    context.setStateA()
    println(context)

    context.setStateB()
    println(context)
}
