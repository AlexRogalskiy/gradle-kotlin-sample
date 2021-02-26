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

import org.gradle.api.Project
import java.io.File

private val whitespaceRegex = Regex("\\s")

internal val String.dotIdentifier
    get() = replace("-", "")
        .replace(".", "")
        .replace(whitespaceRegex, "")

internal val Project.dotIdentifier get() = "$group$name".dotIdentifier

internal fun String.nonEmptyPrepend(prepend: String) =
    if (isNotEmpty()) prepend + this else this

internal fun String.toHyphenCase(): String {
    if (isBlank()) return this

    return this[0].toLowerCase().toString() + toCharArray()
        .map { it.toString() }
        .drop(1)
        .joinToString(separator = "") { if (it[0].isUpperCase()) "-${it[0].toLowerCase()}" else it }
}

/**
 * Executes the given command in specified working dir
 *
 * @param workingDir represents dir where executable command will be executed
 * @param fallback replacement String in case source is empty
 */
internal fun String?.execute(workingDir: File, fallback: String): String {
    Runtime.getRuntime().exec(this, null, workingDir).let {
        it.waitFor()
        return try {
            it.inputStream.reader().readText().trim().letIfEmpty(fallback)
        } catch (e: Exception) {
            fallback
        }
    }
}

/**
 * Lets another string to be used in case source is empty
 *
 * @param fallback replacement String if source is empty
 */
internal fun String?.letIfEmpty(fallback: String): String {
    return if (this == null || isEmpty()) fallback else this
}

/**
 * Applies semantic versioning and returns the combined version name accordingly
 *
 * @return The version name
 */
internal fun getSemanticAppVersionName(): String {
    val majorCode = 1
    val minorCode = 0
    val patchCode = 0

    return "$majorCode.$minorCode.$patchCode"
}
