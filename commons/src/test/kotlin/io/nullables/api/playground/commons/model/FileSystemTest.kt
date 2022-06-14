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
package io.nullables.api.playground.commons.model

import io.nullables.api.playground.commons.properties.DefaultSchemaProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

/**
 * @author Pavel Bodiachevskii
 */
class FileSystemTest {

    @Test
    fun `save with default path`() {
        FileSystem.save("StreetLights-asyncapi.json", "{}", "")
        val savedSchema = File("${DefaultSchemaProperties.filePath}/StreetLights-asyncapi.json")
        savedSchema.deleteOnExit()

        Assertions.assertTrue(savedSchema.exists())
    }

    @Test
    fun `save with given path`() {
        val path = "asyncapi-schemas"

        FileSystem.save("StreetLights-asyncapi.json", "{}", path)
        val savedSchema = File("$path/StreetLights-asyncapi.json")
        savedSchema.deleteOnExit()

        Assertions.assertTrue(savedSchema.exists())
    }
}
