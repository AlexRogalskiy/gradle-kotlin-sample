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
pluginManagement {
  repositories {
    mavenCentral()
    jcenter()
    gradlePluginPortal()

    maven("https://dl.bintray.com/kotlin/kotlin-eap/")
    maven("https://dl.bintray.com/kotlin/kotlin-dev/")
    maven("https://dl.bintray.com/kotlin/kotlinx/")
    maven("https://plugins.gradle.org/m2/")
  }
}

// build scan plugin can only be applied in settings file
plugins {
  id("com.gradle.enterprise") version "3.5.1"
  id("com.pablisco.gradle.automodule") version "0.15"
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

rootProject.name = "gradle-kotlin-sample"

include(
  "appflow",
  "testflow",
  "patterns"
)
