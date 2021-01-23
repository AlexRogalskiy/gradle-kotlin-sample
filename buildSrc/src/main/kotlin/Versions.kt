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
object Versions {
    const val DETEKT_VERSION = "1.15.0"

    const val PROJECT_VERSION: String = "1.0.0"
    const val JVM_TARGET: String = "1.8"
    const val API_VERSION: String = "1.4"
    const val LANGUAGE_VERSION: String = "1.4"
    const val JACOCO: String = "0.8.6"
    const val KTLINT: String = "0.37.2"

    fun currentOrSnapshot(): String {
        if (System.getProperty("snapshot")?.toBoolean() == true) {
            return "$PROJECT_VERSION-SNAPSHOT"
        }
        return PROJECT_VERSION
    }
}
