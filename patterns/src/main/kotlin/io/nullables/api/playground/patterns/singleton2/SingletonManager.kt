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

import java.util.HashMap

/**
 * Created by Inno Fang on 2017/8/12.
 */
class SingletonManager {
    private val objectMap = HashMap<String, Any>()

    private fun SingletonManager() {}

    fun registerService(key: String, instance: Any) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance)
        }
    }

    fun getService(key: String): Any {
        return objectMap[key]!!
    }
}
