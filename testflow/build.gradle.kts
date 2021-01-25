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
plugins {
  java
}

repositories {
  mavenCentral()
  jcenter()
  maven("https://dl.bintray.com/spekframework/spek-dev")
}

dependencies {
  implementation(kotlin("script-runtime"))
  implementation(kotlin("script-util"))
  implementation(kotlin("scripting-compiler-embeddable"))

  // strikt assertion library
  implementation("io.strikt:strikt-core:${Dependencies.Libs.STRIKT_VERSION}")
  implementation("io.strikt:strikt-arrow:${Dependencies.Libs.STRIKT_VERSION}")

  // mockk library
  implementation("io.mockk:mockk:${Dependencies.Libs.MOCKK_VERSION}")

  // kluent assertion library
  compileOnly("org.amshove.kluent:kluent:${Dependencies.Libs.KLUENT_VERSION}")

  // spek DSL library
  compileOnly("org.spekframework.spek2:spek-dsl-jvm:${Dependencies.Libs.SPEK_VERSION}") {
    exclude(group = "org.jetbrains.kotlin")
  }
  compileOnly(
    "org.spekframework.spek2:spek-runner-junit5:${Dependencies.Libs.SPEK_VERSION}"
  ) {
    exclude(group = "org.junit.platform")
    exclude(group = "org.jetbrains.kotlin")
  }

  // spek requires kotlin-reflect, can be omitted if already in the classpath
  runtimeOnly("org.jetbrains.kotlin:kotlin-script-runtime:${Dependencies.Libs.KOTLIN_REFLECT_VERSION}")
  runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:${Dependencies.Libs.KOTLIN_REFLECT_VERSION}")
}

object Dependencies {
  object Libs {
    const val KOTLIN_REFLECT_VERSION = "1.4.30-RC"
    const val STRIKT_VERSION = "0.28.1"
    const val MOCKK_VERSION = "1.10.5"
    const val KLUENT_VERSION = "1.64"
    const val SPEK_VERSION = "2.0.9"
  }
}
