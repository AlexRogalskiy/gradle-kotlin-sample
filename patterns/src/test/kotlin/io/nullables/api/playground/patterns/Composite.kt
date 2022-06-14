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

open class Equipment(
    open val price: Int,
    val name: String
)

open class Composite(name: String) : Equipment(0, name) {

    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.map { it.price }.sum()


    fun add(equipment: Equipment) =
        apply { equipments.add(equipment) }
}

class PersonalComputer : Composite("PC")
class Processor : Equipment(1070, "Processor")
class HardDrive : Equipment(250, "Hard Drive")
class Memory : Equipment(280, "Memory")


class CompositeTest {

    @Test
    fun Composite() {
        val pc = PersonalComputer()
            .add(Processor())
            .add(HardDrive())
            .add(Memory())

        println(pc.price)

        assertThat(pc.name).isEqualTo("PC")
        assertThat(pc.price).isEqualTo(1600)
    }
}
