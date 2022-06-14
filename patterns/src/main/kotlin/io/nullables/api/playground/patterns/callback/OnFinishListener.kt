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
package io.nullables.api.playground.patterns.callback

interface OnFinishListener {
    fun onFinish()
}

class Counter {
    lateinit var onProgress: (progress: Int) -> Unit

    private lateinit var onStart: () -> Unit
    private lateinit var onFinishListener: OnFinishListener

    fun onStart(block: () -> Unit) {
        onStart = block
    }

    fun onFinish(onFinishListener: OnFinishListener) {
        this.onFinishListener = onFinishListener
    }

    operator fun invoke() {
        onStart()

        for (i in 1 until 10000) {
            onProgress(i)
        }

        onFinishListener.onFinish()
    }
}

fun main(args: Array<String>) {
    val counter = Counter()

    counter.onStart {
        println("Counter started")
    }

    counter.onProgress = { progress ->
        if (progress > 9995) {
            println("Progress $progress")
        }
    }

    counter.onFinish(object : OnFinishListener {
        override fun onFinish() {
            println("Counter finished")
        }
    })

    counter()
}
