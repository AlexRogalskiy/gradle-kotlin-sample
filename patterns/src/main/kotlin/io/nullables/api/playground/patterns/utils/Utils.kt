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
package io.nullables.api.playground.patterns.utils

import java.net.URL

object Utils {

    val urlResult: URL = parseUrl("http://someurl")

    enum class ExitCode {
        COMPILATION_ERROR,
        INTERNAL_ERROR,
        SCRIPT_EXECUTION_ERROR,
        OK
    }

    fun parseUrl(url: String): URL =
        kotlin.runCatching { URL(url) }.getOrThrow()

    fun throwGradleExceptionIfError(exitCode: ExitCode) {
        when (exitCode) {
            ExitCode.COMPILATION_ERROR -> throw IllegalArgumentException("Compilation error. See log for more details")
            ExitCode.INTERNAL_ERROR -> throw IllegalArgumentException("Internal compiler error. See log for more details")
            ExitCode.SCRIPT_EXECUTION_ERROR -> throw IllegalArgumentException("Script execution error. See log for more details")
            ExitCode.OK -> {
            }
        }

        throw IllegalStateException("Unexpected exit code: $exitCode")
    }
}
