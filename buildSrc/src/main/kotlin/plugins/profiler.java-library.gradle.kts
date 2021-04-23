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
package plugins

import org.gradle.internal.os.OperatingSystem

plugins {
    id("java-library")
}

repositories {
    jcenter()
    maven {
        url = uri("https://repo.gradle.org/gradle/repo")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

project.extensions.create<Versions>("versions")

abstract class Versions {
    val toolingApi = "org.gradle:gradle-tooling-api:6.6.1"
    val groovy = "org.codehaus.groovy:groovy:2.4.7"
    val spock = "org.spockframework:spock-core:1.1-groovy-2.4"
}

tasks.withType<Test>().configureEach {
    // Add OS as inputs since tests on different OS may behave differently https://github.com/gradle/gradle-private/issues/2831
    // the version currently differs between our dev infrastructure, so we only track the name and the architecture
    inputs.property("operatingSystem", "${OperatingSystem.current().name} ${System.getProperty("os.arch")}")
}
