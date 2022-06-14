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
package io.nullables.api.playground.patterns.observer

import java.util.*

@SuppressWarnings("deprecated")
class Printer : Observer {
    override fun update(p0: Observable?, p1: Any?) {
        val value = p1 as Int
        println(value)
    }
}

@SuppressWarnings("deprecated")
class RandomGenerator : Observable() {

    fun newRandom() {
        setChanged()
        notifyObservers(Random().nextInt())
    }
}

fun main(args: Array<String>) {
    val p = Printer()
    val r = RandomGenerator()

    r.addObserver(p)
    r.newRandom()
    r.newRandom()
    r.newRandom()
}
