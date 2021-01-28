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
package constants

object Dependencies {

    object Core {
        // Kotlin library dependencies
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val kotlin_stdlib_common =
            "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
        const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

        // Kotlinx library dependencies
        const val kotlinx_coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"
        const val kotlinx_serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinx_serialization}"

        // Clikt library dependencies
        const val clikt =
            "com.github.ajalt:clikt:${Versions.clikt}"

        // Arrow library dependencies
        const val arrow_meta =
            "io.arrow-kt:arrow-meta:${Versions.arrow}"
        const val arrow_annotations =
            "io.arrow-kt:arrow-annotations:${Versions.arrow}"
        const val arrow_core =
            "io.arrow-kt:arrow-core:${Versions.arrow}"
        const val arrow_fx =
            "io.arrow-kt:arrow-fx:${Versions.arrow}"
        const val arrow_fx_rx2 =
            "io.arrow-kt:arrow-fx-rx2:${Versions.arrow}"
        const val arrow_optics =
            "io.arrow-kt:arrow-optics:${Versions.arrow}"
        const val arrow_ui =
            "io.arrow-kt:arrow-ui:${Versions.arrow}"
        const val arrow_validation =
            "io.arrow-kt:arrow-validation:${Versions.arrow}"
        const val arrow_mtl =
            "io.arrow-kt:arrow-mtl:${Versions.arrow}"
        const val arrow_syntax =
            "io.arrow-kt:arrow-syntax:${Versions.arrow}"
        const val arrow_data =
            "io.arrow-kt:arrow-core-data:${Versions.arrow}"

        // Jackson library dependencies
        const val jackson_core =
            "com.fasterxml.jackson.core:jackson-core:${Versions.jackson}"
        const val jackson_databind =
            "com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}"

        // Klaxon library dependencies
        const val klaxon =
            "com.beust:klaxon:${Versions.klaxon}"

        // Vavr library dependencies
        const val vavr =
            "io.vavr:vavr-kotlin:${Versions.vavr}"

        // Logback dependencies
        const val logback =
            "ch.qos.logback:logback-classic:${Versions.logback}"

        // RxJava dependencies
        const val rxjava =
            "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    }

    object Test {
        // Kotlin dependencies
        const val kotlin_test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
        const val kotlin_test_common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        const val kotlin_test_annotations_common =
            "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"

        // Mock dependencies
        const val mockk = "io.mockk:mockk:${Versions.mockk}"

        // Junit5 dependencies
        const val junit_runner =
            "org.junit.platform:junit-platform-runner:${Versions.junit_platform}"
        const val junit_launcher =
            "org.junit.platform:junit-platform-launcher:${Versions.junit_platform}"
        const val junit_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter}"
        const val junit_engine =
            "org.junit.jupiter:junit-jupiter-engine:${Versions.junit_jupiter}"

        // (Optional) If you need "Parameterized Tests"
        const val junit_params =
            "org.junit.jupiter:junit-jupiter-params:${Versions.junit_jupiter}"

        // (Optional) If you also have JUnit 4-based tests dependencies
        const val junit_vintage =
            "org.junit.vintage:junit-vintage-engine:${Versions.junit_jupiter}"

        // Kotlinx test dependencies
        const val kotlinx_coroutines_test =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlin_coroutines}"

        // Kotest test dependencies
        const val kotest_assertions_arrow =
            "io.kotest:kotest-assertions-arrow-jvm:${Versions.kotest_junit}"
        const val kotest_assertions_core =
            "io.kotest:kotest-assertions-core-jvm:${Versions.kotest_junit}"
        const val kotest_property =
            "io.kotest:kotest-property-jvm:${Versions.kotest_junit}"
        const val kotest_console =
            "io.kotest:kotest-runner-console-jvm:${Versions.kotest_console}"
        const val kotest_junit =
            "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest_junit}"

        // Kotlin faker test dependencies
        const val kotlin_faker =
            "io.github.serpro69:kotlin-faker:${Versions.kotlin_faker}"

        // Kotlin junit5 test dependencies
        const val kotlin_test_junit =
            "io.kotlintest:kotlintest-runner-junit5:${Versions.kotlin_test}"

        // reflections test dependencies
        const val reflections =
            "org.reflections:reflections:${Versions.reflections}"

        // spek2 test dependencies
        const val spek2_dsl =
            "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek2}"
        const val spek2_junit =
            "org.spekframework.spek2:spek-runner-junit5:${Versions.spek2}"
    }
}
