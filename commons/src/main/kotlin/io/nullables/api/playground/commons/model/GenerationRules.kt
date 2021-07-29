package io.nullables.api.playground.commons.model

import io.nullables.api.playground.commons.properties.DefaultSchemaProperties

/**
 * AsyncAPI schema generation rules.
 *
 * @param includeNulls include null values to generated schema.
 * @param prettyPrint pretty print schema if it possible.
 *
 * @author Pavel Bodiachevskii
 */
data class GenerationRules(
    val includeNulls: Boolean = DefaultSchemaProperties.schemaIncludeNulls,
    val prettyPrint: Boolean = DefaultSchemaProperties.schemaPrettyPrint
)
