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
package io.nullables.api.playground.patterns.mediator

class Mediator(val users: MutableList<User> = ArrayList()) {
    fun sendMessage(message: String, sender: User) {
        users.filter {
            it !== sender
        }.forEach {
            it.receive(message)
        }
    }
}

class User(val name: String, val mediator: Mediator) {

    init {
        mediator.users.add(this)
    }

    fun receive(msg: String) {
        println("$name has been received: $msg")
    }

    fun send(msg: String) {
        mediator.sendMessage(msg, this)
    }
}

fun main(args: Array<String>) {
    val mediator = Mediator()
    User("John Doe", mediator)
    User("Doe John", mediator)
    User("Dohn Joe", mediator)
    User("User1", mediator).send("Hi everyOne")
}
