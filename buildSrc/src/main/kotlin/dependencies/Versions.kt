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
package dependencies

object Versions {
    // core
    const val kotlin = "1.4.21"
    const val kotlinx_serialization = "0.20.0"
    const val kotlin_coroutines = "1.3.4"
    const val rxjava = "2.2.20"
    const val logback = "1.2.3"
    const val arrow = "0.11.0"

    // checkstyle
    const val detekt = "1.15.0"
    const val jacoco: String = "0.8.6"
    const val ktlint: String = "0.37.2"

    // test
    const val mockk = "1.10.0"
    const val junit_jupiter = "5.7.0"
    const val junit_platform = "1.7.0"
    const val kotlin_test = "3.4.2"
    const val kotlin_faker = "1.6.0"
    const val kotest_console = "4.1.3.2"
    const val kotest_junit = "4.3.2"
    const val kotlinx = "1.4.2"
    const val klaxon = "5.4"
    const val clikt = "2.6.0"
    const val reflections = "0.9.12"
    const val spek2 = "2.0.9"

    // project
    const val project: String = "1.0.0"

    fun currentOrSnapshot(): String {
        if (System.getProperty("snapshot")?.toBoolean() == true) {
            return "$project-SNAPSHOT"
        }
        return project
    }
}
