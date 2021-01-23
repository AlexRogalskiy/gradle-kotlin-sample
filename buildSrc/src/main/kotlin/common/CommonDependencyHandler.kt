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
package common

import dependencies.Dependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * A common JUnit5 test dependency handler among all sub projects. This only should be called in
 * case any sub project needs test and androidTest implementations.
 */
fun DependencyHandler.addJUnit5TestDependencies() {
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testRuntimeOnly(Dependencies.Test.junit_runner)
    testRuntimeOnly(Dependencies.Test.junit_launcher)
    testImplementation(Dependencies.Test.junit_api)
    testRuntimeOnly(Dependencies.Test.junit_engine)
    testImplementation(Dependencies.Test.junit_params)
    testRuntimeOnly(Dependencies.Test.junit_vintage)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.testRuntimeOnly(dependencyNotation: Any): Dependency? =
    add("testRuntimeOnly", dependencyNotation)
