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

object Libs {
    const val kotlinVersion = "1.4.21"
    const val org = "io.nullables.api.sample"

    object Kotlin {
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    }

    object Arrow {
        const val Validation = "io.arrow-kt:arrow-validation:0.11.0"
        const val Data = "io.arrow-kt:arrow-core-data:0.11.0"
    }

    object Jackson {
        const val core = "com.fasterxml.jackson.core:jackson-core:2.10.3"
        const val databind = "com.fasterxml.jackson.core:jackson-databind:2.10.3"
    }

    object Kotest {
        private const val version = "4.3.1"
        const val assertions = "io.kotest:kotest-assertions-core-jvm:$version"
        const val junit5 = "io.kotest:kotest-runner-junit5-jvm:$version"
    }

    object Vavr {
        const val kotlin = "io.vavr:vavr-kotlin:0.10.2"
    }
}
