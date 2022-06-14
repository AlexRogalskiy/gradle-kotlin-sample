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

/**
 * Holds schema file settings.
 *
 * @param path where to store generated file.
 * @param format how to store file's content.
 * @param namePostfix file's name postfix.
 *
 * @author Pavel Bodiachevskii
 * @since 1.0.0-RC1
 */
data class SchemaFileSettings(
    val path: String = DefaultSchemaProperties.filePath,
    @Deprecated("unused", level = DeprecationLevel.WARNING)
    val format: String,
    val namePostfix: String = DefaultSchemaProperties.fileNamePostfix
)
