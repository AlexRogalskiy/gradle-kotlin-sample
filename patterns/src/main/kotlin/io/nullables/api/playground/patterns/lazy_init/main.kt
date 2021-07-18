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
package io.nullables.api.playground.patterns.lazy_init

/**
 * Lateinit allows us to tell the compiler that this variable will be asigned later, if we try to access an
 * unitialized variable we get an UninitializedPropertyAccessException exception thrown.
 * */
fun main(args: Array<String>) {
    val deferredClass = Deferred()

    try {
        deferredClass.printDeferred()
    } catch (e: UninitializedPropertyAccessException) {
        println("Exception thrown!")
    }

    deferredClass.deferred = "This variable is now initialized so we can access it"
    deferredClass.printDeferred()
}

class Deferred {
    lateinit var deferred: String

    fun printDeferred() {
        println("deferred = ${deferred}")
    }
}
