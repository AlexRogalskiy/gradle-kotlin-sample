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
package utils

import org.gradle.api.Project
import java.io.File

object AppConfig {

    object Build {
        const val APPLICATION_ID = "meow.bottomnavigation"
        const val APP_MODULE = "Sample"
        const val APP_PACKAGE = "meow/bottomnavigation_sample"
        const val LIBRARY_MODULE = "MeowBottomNavigation"
        const val LIBRARY_PACKAGE = "meow.bottomnavigation"
        const val SRC_MAIN = "src/main/"

        enum class PHASE(var alias: String) {
            ALPHA("alpha"),
            BETA("beta"),
            CANARY("canary"),
            RC("rc"),
            STABLE("")
        }
    }

    object Versions {
        const val API = 1
        const val MAJOR = 1
        const val MINOR = 3
        const val PATCH = 1

        val BUILD_PHASE = Build.PHASE.STABLE

        const val KOTLIN = "1.4.21"
    }

    object Dependencies {
        //        Dependencies.implementationItems.forEach {
        //            implementation(it)
        //        }
        val implementationItems = arrayOf(
            // Kotlin
            kotlin("stdlib-jdk8", Versions.KOTLIN)
        )

        val kaptItems = arrayListOf<String>()
    }

    fun generateVersionCode(): Int {
        return Versions.API * 10000000 + Versions.BUILD_PHASE.ordinal * 1000000 + Versions.MAJOR * 10000 + Versions.MINOR * 100 + Versions.PATCH
    }

    fun generateVersionName(): String {
        val type = if (Versions.BUILD_PHASE.alias == "") "" else "-${Versions.BUILD_PHASE.alias}"
        return "${Versions.MAJOR}.${Versions.MINOR}.${Versions.PATCH}$type"
    }
}

fun <T> Project.getPropertyAny(key: String): T {
    val properties = java.util.Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }
    @Suppress("UNCHECKED_CAST")
    return properties.getProperty(key) as T
}

fun kotlinx(module: String, version: String? = null): Any =
    "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"

fun kotlin(module: String, version: String? = null): Any =
    "org.jetbrains.kotlin:kotlin-$module${version?.let { ":$version" } ?: ""}"

fun getAllResourcesSrcDirs(project: Project, isLibrary: Boolean = false): ArrayList<String> {
    val moduleName = if (isLibrary) AppConfig.Build.LIBRARY_MODULE else AppConfig.Build.APP_MODULE
    val packageName =
        if (isLibrary) AppConfig.Build.LIBRARY_PACKAGE else AppConfig.Build.APP_PACKAGE
    val list = arrayListOf<String>()
    val path =
        project.rootDir.absolutePath + "\\" + moduleName + "\\src\\main\\kotlin\\" + packageName
    val root = File(path)

    list.add(project.rootDir.absolutePath + "\\" + moduleName + "\\src\\main\\res")

    root.listDirectoriesWithChild().forEach { directory ->
        if (directory.isRes())
            list.add(directory.path)
    }

    return list
}

fun File.listDirectories() = listFiles()?.filter { it.isDirectory } ?: arrayListOf()
fun File.listDirectoriesWithChild(): List<File> {
    val list = ArrayList<File>()

    fun File.findAllDirectories(list: ArrayList<File>) {
        listDirectories().forEach {
            it.findAllDirectories(list)
            list.add(it)
        }
    }

    findAllDirectories(list)
    return list
}

fun File.isRes() = name == "res"
