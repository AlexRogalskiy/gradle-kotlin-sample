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

import Config
import java.util.*

/**
 * A helper function to check whether or not requested dependency is up-to-date
 *
 * @param version The version
 *
 * @return true if the dependency is under any of the specified version suffix otherwise false
 */
fun isNonStable(version: String): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase(Locale.ROOT).contains(it) }
    val regex = Config.BUILD_STABLE_REGEX.toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
