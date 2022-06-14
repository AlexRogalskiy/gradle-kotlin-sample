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
package io.nullables.api.playground.commons.model

/**
 * @author Pavel Bodiachevskii
 */
data class GenerationSources(
    val classes: Array<String>,
    val packages: Array<String>,
    val classLoader: ClassLoader
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GenerationSources

        if (!classes.contentEquals(other.classes)) return false
        if (!packages.contentEquals(other.packages)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = classes.contentHashCode()
        result = 31 * result + packages.contentHashCode()
        return result
    }
}
