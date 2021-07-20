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
package io.nullables.api.playground.patterns.fan_out

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlin.random.Random

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceTasks(length: Int) = produce {
    // Generate sequence of integers to send to a channel
    for (i in 1..length) {
        send(i)
    }
}

suspend fun consumerWithRandomDelay(task: Int) {
    // The delay simulates a different times of processing
    // a job
    delay(Random.nextInt(100, 900).toLong())
    println("Consumed $task")
}

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        // The produceTasks function will return a channel on which
        // consumers can receive
        val resultsChannel = produceTasks(10_000)

        for (r in resultsChannel) {
            // Every time a message is received through a channel
            // launch a new coroutine
            launch {
                consumerWithRandomDelay(r)
            }
        }
    }
}
