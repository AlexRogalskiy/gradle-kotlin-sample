package extensions

import org.gradle.api.artifacts.dsl.RepositoryHandler

/**
 * Applies default plugins for repository
 */
fun RepositoryHandler.applyDefaults() {
    google()
    mavenCentral()
    jcenter()
}
