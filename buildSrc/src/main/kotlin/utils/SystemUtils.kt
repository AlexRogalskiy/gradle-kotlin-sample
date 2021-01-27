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
package utils

import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Returns the corresponding [JavaVersion]
 */
inline val javaVersion: JavaVersion get() = JavaVersion.VERSION_1_8

/**
 * Returns the corresponding [KotlinVersion]
 */
inline val kotlinVersion: KotlinVersion get() = KotlinVersion.CURRENT

/**
 * Returns the parallel forks for testing
 */
internal inline val parallelForks: Int
    get() = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1

/**
 * Usage: <code>./gradlew build -PwarningsAsErrors=true</code>.
 */
internal fun shouldTreatCompilerWarningsAsErrors(project: Project): Boolean {
    return project.findProperty("warningsAsErrors") == "true"
}

/**
 * Util to check if the project run on Linux or Mac operating system
 *
 * @return true if the operating system is one of them
 */
fun isLinuxOrMacOs(): Boolean {
    val osName = System.getProperty("os.name").toLowerCase(Locale.ROOT)
    return listOf("linux", "mac os", "macos").contains(osName)
}

fun Project.assertDefaultConfigUpToDate(defaultConfigFile: String = "$projectDir/config/detekt/detekt.yml") {
    val configDiff = ByteArrayOutputStream()

    exec {
        commandLine = listOf("git", "diff", defaultConfigFile)
        standardOutput = configDiff
    }

    if (configDiff.toString().isNotEmpty()) {
        throw GradleException(
            "The default-detekt-config.yml is not up-to-date. " +
                "You can execute the generateDocumentation Gradle task to update it and commit the changed files."
        )
    }
}
