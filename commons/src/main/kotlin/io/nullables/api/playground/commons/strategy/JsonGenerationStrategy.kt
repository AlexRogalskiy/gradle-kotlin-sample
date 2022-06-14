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
package io.nullables.api.playground.commons.strategy

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.nullables.api.playground.commons.exception.AsyncAPISchemaGenerationException
import io.nullables.api.playground.commons.interfaces.GenerationStrategy
import io.nullables.api.playground.commons.asyncapi.AsyncAPISchemaLoader
import io.nullables.api.playground.commons.model.FileSystem
import io.nullables.api.playground.commons.model.GenerationSettings

/**
 * AsyncAPI schema generation strategy in json format.
 *
 * @param generationSettings schema generation settings.
 *
 * @author Pavel Bodiachevskii
 * @since 1.0.0-RC1
 */
class JsonGenerationStrategy(
    private val generationSettings: GenerationSettings
) : GenerationStrategy {

    private val asyncAPISchemaLoader =
        AsyncAPISchemaLoader(generationSettings.logger, generationSettings.sources)

    private val objectMapper: ObjectMapper by lazy {
        val instance = ObjectMapper()

        if (!generationSettings.rules.includeNulls) {
            instance.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }

        instance
    }

    @Throws(AsyncAPISchemaGenerationException::class)
    override fun generate() {
        val schemas = asyncAPISchemaLoader.load()
        schemas.forEach(this::save)
    }

    private fun save(schemaClass: Class<*>) {
        val schema = serialize(schemaClass)
        val schemaFileName =
            "${schemaClass.simpleName}-${generationSettings.schemaFile.namePostfix}.json"
        val schemaFilePath = generationSettings.schemaFile.path

        generationSettings.logger.info("saving ${schemaClass.name} to $schemaFilePath")
        FileSystem.save(schemaFileName, schema, schemaFilePath)
    }

    @Throws(AsyncAPISchemaGenerationException::class)
    @Suppress("Duplicates")
    private fun serialize(schemaClass: Class<*>): String {
        return try {
            val foundAsyncAPI = schemaClass.newInstance()

            if (generationSettings.rules.prettyPrint) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(foundAsyncAPI)
            } else {
                objectMapper.writeValueAsString(foundAsyncAPI)
            }
        } catch (exception: Exception) {
            throw AsyncAPISchemaGenerationException(
                "Can't serialize: ${schemaClass.simpleName}. Because of: ${exception.message}",
                exception
            )
        }
    }

}
