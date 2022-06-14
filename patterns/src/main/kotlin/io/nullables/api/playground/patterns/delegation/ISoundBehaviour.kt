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

interface ISoundBehaviour {
    fun makeSound()
}

class CatSound : ISoundBehaviour{
    override fun makeSound() {
        println("Meow")
    }
}

class CatRelaxSound : ISoundBehaviour{
    override fun makeSound() {
        println("Prrrrrr")
    }
}


class Cat {
    var sound : ISoundBehaviour = CatSound()

    fun makeSound(){
        this.sound.makeSound()
    }

    fun setSoundBehaviour(newSound : ISoundBehaviour){
        this.sound = newSound
    }
}

fun main(args: Array<String>) {
    val cat : Cat = Cat()
    cat.makeSound() // Meow
    val newSound : ISoundBehaviour = CatRelaxSound()
    cat.setSoundBehaviour(newSound)
    cat.makeSound() // Prrrr
}
