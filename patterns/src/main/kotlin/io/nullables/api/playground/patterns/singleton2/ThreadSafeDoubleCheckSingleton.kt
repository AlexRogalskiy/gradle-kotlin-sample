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
package io.nullables.api.playground.patterns.singleton2

/**
 * Created by Inno Fang on 2017/8/12.
 */
class ThreadSafeDoubleCheckSingleton {
    companion object {
        // implement 1
        val instance1 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ThreadSafeDoubleCheckSingleton()
        }

        // implement 2
        @Volatile private var instance2: ThreadSafeDoubleCheckSingleton? = null

        fun get(): ThreadSafeDoubleCheckSingleton {
            if (null == instance2) {
                synchronized(this) {
                    if (null == instance2) {
                        instance2 = ThreadSafeDoubleCheckSingleton()
                    }
                }
            }
            return instance2!!
        }
    }
}
