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
