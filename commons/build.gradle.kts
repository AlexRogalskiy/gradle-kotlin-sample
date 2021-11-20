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
  kotlin
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.projectlombok:lombok:${Dependencies.Libs.LOMBOK_VERSION}")
  implementation("org.reflections:reflections:${Dependencies.Libs.REFLECTIONS_VERSION}")
  implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:${Dependencies.Libs.JACKSON_VERSION}")

  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:${Dependencies.Libs.JUNIT_VERSION}")
  testImplementation("org.assertj:assertj-core:${Dependencies.Libs.ASSERT_VERSION}")
}

object Dependencies {
  object Libs {
    const val JACKSON_VERSION = "2.12.4"
    const val REFLECTIONS_VERSION = "0.9.12"
    const val FINDBUGS_VERSION = "3.0.2"
    const val LOMBOK_VERSION = "1.18.18"
    const val JUNIT_VERSION = "1.4.30-RC"
    const val ASSERT_VERSION = "3.11.1"
  }
}
