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
package io.nullables.api.playground.patterns

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

data class Memento(val state: String)

class Originator(var state: String) {

    fun createMemento(): Memento {
        return Memento(state)
    }

    fun restore(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = ArrayList<Memento>()

    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int): Memento {
        return mementoList[index]
    }
}

class MementoTest {

    @Test
    fun Memento() {
        val originator = Originator("initial state")
        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())

        originator.state = "State #1"
        originator.state = "State #2"
        careTaker.saveState(originator.createMemento())

        originator.state = "State #3"
        println("Current State: " + originator.state)
        assertThat(originator.state).isEqualTo("State #3")

        originator.restore(careTaker.restore(1))
        println("Second saved state: " + originator.state)
        assertThat(originator.state).isEqualTo("State #2")

        originator.restore(careTaker.restore(0))
        println("First saved state: " + originator.state)
        assertThat(originator.state).isEqualTo("initial state")
    }
}
