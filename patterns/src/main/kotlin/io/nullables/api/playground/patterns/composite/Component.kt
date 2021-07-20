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
package io.nullables.api.playground.patterns.composite

interface Component {
    fun println()
}

class Leaf(private val nome: String) : Component {
    override fun println() {
        println(nome)
    }
}

class Composite(private val nome: String) : Component {
    private val components = ArrayList<Component>()

    override fun println() {
        println(nome)

        components.forEach {
            it.println()
        }
    }

    fun addComponent(vararg c: Component) {
        c.forEach {
            components.add(it)
        }
    }
}

fun main(args: Array<String>) {
    val eyes = Composite("eyes")
    val reye = Leaf("reye")
    val leye = Leaf("leye")
    eyes.addComponent(reye, leye)
    val nose = Leaf("nose")
    val face = Composite("face")
    val mouth = Leaf("mouth")
    face.addComponent(eyes, nose, mouth)
    face.println()
}
