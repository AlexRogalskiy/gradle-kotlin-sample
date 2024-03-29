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

import io.nullables.api.playground.commons.model.GenerationSettings
import io.nullables.api.playground.commons.properties.DefaultSchemaProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

/**
 * @author Pavel Bodiachevskii
 */
class YamlGenerationStrategyTest : GenerationStrategyTest() {

    private fun composeGenerationSettings(
        classes: Array<String> = emptyArray(),
        packages: Array<String> = emptyArray(),
        path: String = DefaultSchemaProperties.filePath,
        namePostfix: String = DefaultSchemaProperties.fileNamePostfix
    ): GenerationSettings {
        return GenerationSettings(
            logger,
            composeGenerationSources(classes, packages),
            composeGenerationRules(),
            composeSchemaFileSettings(path, namePostfix)
        )
    }

    /*
        Default schema properties.
     */
    @Test
    fun `generate with default schema properties when classes and packages are empty`() {
        val yamlGenerationStrategy = YamlGenerationStrategy(composeGenerationSettings())
        yamlGenerationStrategy.generate()

        val schemasFolder = File(defaultPath)
        Assertions.assertFalse(
            schemasFolder.exists(),
            "Folders must not be created in case when classes & packages are empty."
        )
    }

    /*
        Custom path.
     */
    @Test
    fun `generate with given path when classes and packages are empty`() {
        val yamlGenerationStrategy =
            YamlGenerationStrategy(composeGenerationSettings(path = customPath))
        yamlGenerationStrategy.generate()

        val schemasFolder = File(customPath)
        Assertions.assertFalse(
            schemasFolder.exists(),
            "Folders must not be created in case when classes & packages are empty."
        )
    }

    @ParameterizedTest
    @MethodSource("schemaGenerationSettings")
    fun generate(settings: GenerationSettings) {
        val yamlGenerationStrategy = YamlGenerationStrategy(settings)
        yamlGenerationStrategy.generate()

        val folderWithSchemas = File(settings.schemaFile.path)
        val expectedSchemaFileNameEnding = "${settings.schemaFile.namePostfix}.yaml"

        Assertions.assertTrue(
            folderWithSchemas.exists() && folderWithSchemas.isDirectory,
            "Path must exists and be folder."
        )
        Assertions.assertTrue(
            folderWithSchemas.listFiles()?.isNotEmpty() ?: false,
            "Folder must not be empty."
        )
        folderWithSchemas.listFiles()?.forEach {
            Assertions.assertTrue(
                it.name.endsWith(expectedSchemaFileNameEnding),
                "Schema must ends with: $expectedSchemaFileNameEnding"
            )
        }
        folderWithSchemas.listFiles()?.forEach {
            validateSchemaContent(it, settings.rules, "yaml")
        }
    }

    companion object {

        @JvmStatic
        fun schemaGenerationSettings(): Stream<Arguments> {
            val yamlGenerationStrategyTest = YamlGenerationStrategyTest()

            /*
                * with default schema properties when classes are not empty
                * with default schema properties when packages are not empty
                * with default schema properties when classes and packages are not empty
                *
                * with given path when classes are not empty
                * with given path when packages are not empty
                * with given path when classes and packages are not empty
                *
                * with given path, namePostfix when classes are not empty
                * with given path, namePostfix when packages are not empty
                * with given path, namePostfix when classes and packages are not empty
             */
            return Stream.of(
                /*
                    default
                 */
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        classes = arrayOf(
                            "com.asyncapi.schemas.lamps.Lamps",
                            "com.asyncapi.schemas.streetlights.Streetlights"
                        )
                    )
                ),
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        packages = arrayOf(
                            "com.asyncapi.schemas.lamps",
                            "com.asyncapi.schemas.streetlights"
                        )
                    )
                ),
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        classes = arrayOf(
                            "com.asyncapi.schemas.lamps.Lamps",
                            "com.asyncapi.schemas.streetlights.Streetlights"
                        ),
                        packages = arrayOf(
                            "com.asyncapi.schemas.lamps",
                            "com.asyncapi.schemas.streetlights"
                        )
                    )
                ),
                /*
                    path
                 */
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        classes = arrayOf(
                            "com.asyncapi.schemas.lamps.Lamps",
                            "com.asyncapi.schemas.streetlights.Streetlights"
                        ),
                        path = customPath
                    )
                ),
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        packages = arrayOf(
                            "com.asyncapi.schemas.lamps",
                            "com.asyncapi.schemas.streetlights"
                        ),
                        path = customPath
                    )
                ),
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        classes = arrayOf(
                            "com.asyncapi.schemas.lamps.Lamps",
                            "com.asyncapi.schemas.streetlights.Streetlights"
                        ),
                        packages = arrayOf(
                            "com.asyncapi.schemas.lamps",
                            "com.asyncapi.schemas.streetlights"
                        ),
                        path = customPath
                    )
                ),
                /*
                    path, namePostfix
                 */
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        classes = arrayOf(
                            "com.asyncapi.schemas.lamps.Lamps",
                            "com.asyncapi.schemas.streetlights.Streetlights"
                        ),
                        path = customPath,
                        namePostfix = "schema"
                    )
                ),
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        packages = arrayOf(
                            "com.asyncapi.schemas.lamps",
                            "com.asyncapi.schemas.streetlights"
                        ),
                        path = customPath,
                        namePostfix = "schema"
                    )
                ),
                Arguments.of(
                    yamlGenerationStrategyTest.composeGenerationSettings(
                        classes = arrayOf(
                            "com.asyncapi.schemas.lamps.Lamps",
                            "com.asyncapi.schemas.streetlights.Streetlights"
                        ),
                        packages = arrayOf(
                            "com.asyncapi.schemas.lamps",
                            "com.asyncapi.schemas.streetlights"
                        ),
                        path = customPath,
                        namePostfix = "schema"
                    )
                )
            )
        }
    }
}
