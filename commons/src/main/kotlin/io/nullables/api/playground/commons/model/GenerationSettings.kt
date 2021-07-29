package io.nullables.api.playground.commons.model

import io.nullables.api.playground.commons.interfaces.Logger

/**
 * @author Pavel Bodiachevskii
 */
data class GenerationSettings(
    val logger: Logger,
    val sources: GenerationSources,
    val rules: GenerationRules,
    val schemaFile: SchemaFileSettings
)
