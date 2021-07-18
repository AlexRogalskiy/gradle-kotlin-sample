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
package io.nullables.api.playground.patterns.delegation

interface SoundBehaviour {
    fun makeSound()

    class SoundMaker(private val sound: String) : SoundBehaviour {
        override fun makeSound() {
            println(sound)
        }
    }
}

class CatSound2 private constructor(sound: SoundBehaviour) : SoundBehaviour by sound {
    companion object {
        fun create() = CatSound2(SoundBehaviour.SoundMaker("meow"))
    }
}

class CatRelaxSound2 private constructor(sound: SoundBehaviour) : SoundBehaviour by sound {
    companion object {
        fun create() = CatRelaxSound2(SoundBehaviour.SoundMaker("purrrrrrrr"))
    }
}

class Cat2 {
    var sound: SoundBehaviour = CatSound2.create()
    fun makeSound() = sound.makeSound()
}

fun main(args: Array<String>) {
    val cat: Cat2 = Cat2()

    // Output: meow
    cat.makeSound()

    cat.sound = CatRelaxSound2.create()

    // Output: purrrrrrrr
    cat.makeSound()
}
