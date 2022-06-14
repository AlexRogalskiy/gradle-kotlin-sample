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
package io.nullables.api.playground.patterns.visitor

interface Animal {
    fun accept(animalOperation: AnimalOperation)
}

interface AnimalOperation {
    fun visitMonkey(monkey: Monkey);
    fun visitLion(lion: Lion);
}

class Lion : Animal {
    fun roar() {
        println("Roaaar!")
    }

    override fun accept(animalOperation: AnimalOperation) {
        animalOperation.visitLion(this)
    }
}

class Monkey : Animal {
    fun shout() {
        println("Ooh oo aa aa!")
    }

    override fun accept(animalOperation: AnimalOperation) {
        animalOperation.visitMonkey(this)
    }
}

class SpeakOperation : AnimalOperation {
    override fun visitMonkey(monkey: Monkey) {
        monkey.shout()
    }

    override fun visitLion(lion: Lion) {
        lion.roar()
    }
}

fun main(args: Array<String>) {
    val speakOperation = SpeakOperation()
    val monkey = Monkey()
    monkey.accept(speakOperation)
}
