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
@file:Suppress("unused")

package extensions

import misc.MainSources
import misc.TestSources
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import utils.execute
import utils.getProperty
import utils.shouldTreatCompilerWarningsAsErrors

/**
 * An extension to create main Kotlin source set.
 *
 * @param namedDomainObjectContainer The container to create the corresponding source set
 *
 * @return The main Kotlin [SourceSet]
 */
fun Project.createKotlinMainSources(
    namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>
) = MainSources.create(namedDomainObjectContainer, this)

/**
 * An extension to create test Kotlin source set.
 *
 * @param namedDomainObjectContainer The container to create the corresponding source set
 *
 * @return The test Kotlin [SourceSet]
 */
fun Project.createKotlinTestSources(
    namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>
) = TestSources.create(namedDomainObjectContainer, this)

/**
 * Applies semantic versioning and returns the combined version name accordingly
 *
 * @return The project version name
 */
fun Project.getProjectVersion() = utils.getSemanticAppVersionName()

/**
 * Basically fetches the recent git commit hash
 */
internal inline val Project.gitSha: String
    get() = "git rev-parse --short HEAD".execute(rootDir, "none")

/**
 * Specify whether or not treat compiler warnings as errors
 */
internal fun Project.shouldTreatCompilerWarningsAsErrors() =
    shouldTreatCompilerWarningsAsErrors(this)

/**
 * Returns the requested property
 *
 * @param name The property name
 *
 * @return The property as [String]
 */
fun Project.getProperty(name: String) = getProperty(name, this)
