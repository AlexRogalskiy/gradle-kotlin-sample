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

import java.util.regex.Pattern
import java.util.regex.Pattern.MULTILINE

private const val whitespaceRegex = "\\s"
private const val SPACE_TOMBSTONE = '\u0003'

private const val SLASH_STAR_ESCAPE = "\u0004\u0005"
private const val STAR_SLASH_ESCAPE = "\u0005\u0004"

internal fun indexOfCommentEscapeSequences(s: String) =
    s.indexOfAny(listOf(SLASH_STAR_ESCAPE, STAR_SLASH_ESCAPE))

/**
 * kotlin-compiler's KDoc lexer doesn't correctly handle nested slash-star comments, so we escape
 * them into tombstones, format, then unescape.
 */
internal fun escapeKDoc(s: String): String {
    val startMarkerIndex = s.indexOf("/*")
    val endMarkerIndex = s.lastIndexOf("*/")

    if (startMarkerIndex == -1 || endMarkerIndex == -1) {
        throw RuntimeException("KDoc with no /** and/or */")
    }

    return s.substring(0, startMarkerIndex + 3) +
        s.substring(startMarkerIndex + 3, endMarkerIndex)
            .replace("/*", SLASH_STAR_ESCAPE)
            .replace("*/", STAR_SLASH_ESCAPE) +
        s.substring(endMarkerIndex)
}

internal fun unescapeKDoc(s: String): String =
    s.replace(SLASH_STAR_ESCAPE, "/*").replace(STAR_SLASH_ESCAPE, "*/")

internal val String.dotIdentifier
    get() = replace("-", "")
        .replace(".", "")
        .replace(Regex(whitespaceRegex), "")

internal val Project.dotIdentifier get() = "$group$name".dotIdentifier

internal fun String.indexOfWhitespaceTombstone() = this.indexOf(SPACE_TOMBSTONE)

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
 * Google-java-format removes trailing spaces when it emits formatted code, which is a problem for
 * multiline string literals. We trick it by replacing the last trailing space in such cases with a
 * tombstone, a character that's unlikely to be used in a regular program. After formatting, we
 * replace it back to a space.
 */
internal fun replaceTrailingWhitespaceWithTombstone(s: String): String {
    return Pattern.compile(" ($)", MULTILINE).matcher(s).replaceAll("$SPACE_TOMBSTONE$1")
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
