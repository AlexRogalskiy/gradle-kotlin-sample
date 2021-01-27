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
package misc

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*
import java.io.File
import java.io.FileInputStream
import java.util.*

@CacheableTask
open class ProfileConfigTask : DefaultTask() {

    private val fileProp: RegularFileProperty = project.objects.fileProperty()

    init {
        group = "profiles"
        description = "Build Kotlin configuration profiles from configuration file."
    }

    @get:OutputDirectory
    //get:PathSensitive(PathSensitivity.RELATIVE)
    var generatedSourceOutput: File? = null

    @get:InputFile
    @get:PathSensitive(PathSensitivity.ABSOLUTE)
    var fileToUpdate: File
        get() = fileProp.get().asFile
        set(value) {
            fileProp.set(value)
        }

    @TaskAction
    fun build() {
        generatedSourceOutput?.run {
            deleteRecursively()
            val kotlinConfigGenerator = KotlinConfigGenerator(this)
            kotlinConfigGenerator.generateConfig(getProperties())
        }
    }

    private fun getProperties(): Properties {
        val localPropertiesFile = project.file(fileToUpdate)
        val localProperties = Properties()
        localProperties.load(FileInputStream(localPropertiesFile))
        return localProperties
    }
}
