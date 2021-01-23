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
package misc

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet

/**
 * An object that includes source sets
 */
private object InternalSourceSet {
    const val MAIN = "main"
    const val TEST = "test"
}

/**
 * The common interface to create any source set
 */
@FunctionalInterface
private interface SourceSetCreator {
    /**
     * The val which includes name of the source set from [InternalSourceSet]
     */
    val name: String

    /**
     * Creates the requested source set
     *
     * @param namedDomainObjectContainer The container to create the corresponding source set
     * @param project The project
     *
     * @return The [SourceSet]
     */
    fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
        project: Project
    ): SourceSet
}

/**
 * A [misc.SourceSetCreator] implementation to create main Kotlin [SourceSet]
 */
internal object MainSources : misc.SourceSetCreator {
    override val name = misc.InternalSourceSet.MAIN

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
        project: Project
    ): SourceSet {
        return namedDomainObjectContainer.getByName(name).apply {
            java.srcDir("src/main/kotlin")
        }
    }
}

/**
 * A [misc.SourceSetCreator] implementation to create test Kotlin [SourceSet]
 */
internal object TestSources : misc.SourceSetCreator {
    override val name = misc.InternalSourceSet.TEST

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
        project: Project
    ): SourceSet {
        return namedDomainObjectContainer.getByName(name).apply {
            java.srcDirs("src/sharedTest/kotlin", "src/test/kotlin")
            resources.srcDir("src/test/resources")
        }
    }
}
