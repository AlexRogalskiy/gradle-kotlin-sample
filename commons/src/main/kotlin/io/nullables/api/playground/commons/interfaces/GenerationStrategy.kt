package io.nullables.api.playground.commons.interfaces

import io.nullables.api.playground.commons.exception.AsyncAPISchemaGenerationException

/**
 * AsyncAPI schema generation strategy.
 *
 * @author Pavel Bodiachevskii
 * @since 1.0.0-RC1
 */
interface GenerationStrategy {

    /**
     * Generates AsyncAPI Schema.
     *
     * @throws AsyncAPISchemaGenerationException in case of schema generation issues.
     */
    @Throws(AsyncAPISchemaGenerationException::class)
    fun generate()
}
