package io.nullables.api.playground.commons.model

import io.nullables.api.playground.commons.properties.DefaultSchemaProperties
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author Pavel Bodiachevskii
 */
object FileSystem {

    fun save(name: String, content: String, path: String) {
        val dirPath = if (path.isBlank()) {
            Paths.get(DefaultSchemaProperties.filePath)
        } else {
            Paths.get(path)
        }

        File(Files.createDirectories(dirPath).toFile(), name).writeText(content, Charsets.UTF_8)
    }
}
