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

import io.nullables.api.playground.commons.interfaces.Logger
import io.nullables.api.playground.commons.model.GenerationRules
import io.nullables.api.playground.commons.model.GenerationSources
import io.nullables.api.playground.commons.model.SchemaFileSettings
import io.nullables.api.playground.commons.properties.DefaultSchemaProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import java.io.File
import java.net.URLClassLoader

/**
 * @author Pavel Bodiachevskii
 */
open class GenerationStrategyTest {

    protected val logger = object : Logger {
        override fun info(message: String) {
            println(message)
        }

        override fun error(message: String) {
            println(message)
        }
    }

    @BeforeEach
    private fun deleteGeneratedSchemas() {
        val defaultSchemasFolder = File(defaultPath.split("/")[0])
        val schemasFolder = File(customPath)

        if (defaultSchemasFolder.exists()) {
            defaultSchemasFolder.deleteRecursively()
        }

        if (schemasFolder.exists()) {
            schemasFolder.deleteRecursively()
        }
    }

    protected fun composeGenerationSources(
        classes: Array<String> = emptyArray(),
        packages: Array<String> = emptyArray()
    ): GenerationSources {
        return GenerationSources(classes, packages, URLClassLoader.getSystemClassLoader())
    }

    protected fun composeGenerationRules(
        includeNulls: Boolean = DefaultSchemaProperties.schemaIncludeNulls,
        prettyPrint: Boolean = DefaultSchemaProperties.schemaPrettyPrint
    ): GenerationRules {
        return GenerationRules(includeNulls, prettyPrint)
    }

    protected fun composeSchemaFileSettings(
        path: String = DefaultSchemaProperties.filePath,
        namePostfix: String = DefaultSchemaProperties.fileNamePostfix
    ): SchemaFileSettings {
        return SchemaFileSettings(path, namePostfix)
    }

    protected fun validateSchemaContent(
        schema: File,
        generationRules: GenerationRules,
        format: String
    ) {
        val expectedSchemaName = schema.nameWithoutExtension.split("-")[0] + "-asyncapi.$format"
        val expectedSchemaPath = if (generationRules.prettyPrint) {
            if (generationRules.includeNulls) {
                "schemas/pretty-print/include-nulls"
            } else {
                "schemas/pretty-print/avoid-nulls"
            }
        } else {
            if (!generationRules.includeNulls) {
                "schemas/raw/include-nulls"
            } else {
                "schemas/raw/avoid-nulls"
            }
        }

        Assertions.assertEquals(
            this::class.java.getResource("/$expectedSchemaPath/$expectedSchemaName")
                .readText(Charsets.UTF_8),
            File(schema.path).readText(Charsets.UTF_8),
            "Handwritten schema must be identical to generated."
        )
    }

    companion object {
        const val defaultPath = DefaultSchemaProperties.filePath
        const val customPath = "asyncapi-schemas"
    }
}
