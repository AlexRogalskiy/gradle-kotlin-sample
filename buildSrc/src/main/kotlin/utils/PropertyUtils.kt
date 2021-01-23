package utils

import org.gradle.api.Project
import java.io.File
import java.util.*

private const val VERSION_PROPERTIES_FILE_NAME = "keystore.properties"

/**
 * Returns the requested properties file
 *
 * @param project The main project
 *
 * @return property [File]
 */
internal fun getFile(project: Project) = project.rootProject.file(VERSION_PROPERTIES_FILE_NAME)

/**
 * Returns the requested property
 *
 * @param name The property name
 * @param project The main project
 *
 * @return The property as [String]
 */
internal fun getProperty(name: String, project: Project): String {
    val properties = Properties().apply {
        val versionProperties = getFile(project)
        if (versionProperties.exists()) {
            load(versionProperties.inputStream())
        }
    }
    return properties.getProperty(name)
}
