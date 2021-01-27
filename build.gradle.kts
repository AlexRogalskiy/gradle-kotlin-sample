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
import constants.Dependencies
import plugins.BuildPlugins
import tasks.BuildTasks
import extensions.applyDefaults
import common.addJUnit5TestDependencies

repositories {
  mavenCentral()
  mavenLocal()
  google()
  jcenter {
    content {
      includeGroup("org.jetbrains.kotlinx")
      includeGroup("io.arrow-kt")
    }
  }

  maven("https://oss.sonatype.org/content/repositories/snapshots/")
  maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/")

  maven("https://plugins.gradle.org/m2/")
  maven("https://kotlin.bintray.com/kotlinx")

  maven("https://dl.bintray.com/serpro69/maven/")
  maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  maven("http://dl.bintray.com/kategory/maven")
}

plugins {
  id(Plugins.kotlinJvm)
  id(Plugins.kotlinKapt)
  id(Plugins.shadow)
  id(Plugins.versions)
  //id(Plugins.compatValidator)
  id(Plugins.sonarQube)
}

allprojects {
  repositories.applyDefaults()

  plugins.apply(BuildPlugins.detekt)
  plugins.apply(BuildPlugins.update_dependencies)
  plugins.apply(BuildPlugins.ktlint)
  plugins.apply(BuildPlugins.git_hooks)
  plugins.apply(BuildPlugins.spotless)
  plugins.apply(BuildPlugins.test_logger)
  plugins.apply(BuildPlugins.kotlin_sources)
  plugins.apply(BuildPlugins.dokka)
  plugins.apply(BuildPlugins.tocme)
  // plugins.apply(BuildPlugins.jacoco)
}

subprojects {
  apply(from = "$rootDir/versions.gradle.kts")

  plugins.apply(BuildTasks.COMMON_TASKS)

  kotlin.sourceSets["main"].kotlin.srcDirs("$buildDir/generated/kotlin/config")

  group = project.extra["appGroup"]
  version = project.extra["appVersion"]
  description = project.extra["appDescription"] as String

  dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // kotlin library dependencies
    implementation(Dependencies.Core.kotlin_reflect)
    implementation(Dependencies.Core.kotlin_stdlib)
    implementation(Dependencies.Core.kotlin_stdlib_common)
    implementation(Dependencies.Core.kotlin_stdlib_jdk8)

    // annotation processors
    kapt(Dependencies.Core.arrow_meta)
    kaptTest(Dependencies.Core.arrow_meta)

    // command line args parsing library dependencies
    implementation(Dependencies.Core.clikt)

    // arrow library dependencies
    implementation(Dependencies.Core.arrow_annotations)
    implementation(Dependencies.Core.arrow_core)
    implementation(Dependencies.Core.arrow_fx)
    implementation(Dependencies.Core.arrow_fx_rx2)
    implementation(Dependencies.Core.arrow_optics)
    implementation(Dependencies.Core.arrow_ui)
    implementation(Dependencies.Core.arrow_validation)
    implementation(Dependencies.Core.arrow_mtl)
    implementation(Dependencies.Core.arrow_syntax)

    // json parsing library dependencies
    implementation(Dependencies.Core.klaxon)

    // logging library dependencies
    implementation(Dependencies.Core.logback)

    // rxjava library dependencies
    implementation(Dependencies.Core.rxjava)

    // kotlin library dependencies
    implementation(Dependencies.Core.kotlin_stdlib)

    // kotlinx library dependencies
    implementation(Dependencies.Core.kotlinx_coroutines)
    implementation(Dependencies.Core.kotlinx_serialization)

    // kotlin test library dependencies
    testImplementation(Dependencies.Test.kotlin_test)
    testImplementation(Dependencies.Test.kotlin_test_common)
    testImplementation(Dependencies.Test.kotlin_test_annotations_common)

    // kotest library dependencies
    testImplementation(Dependencies.Test.kotest_assertions_arrow)
    testImplementation(Dependencies.Test.kotest_assertions_core)
    testImplementation(Dependencies.Test.kotest_property)
    testImplementation(Dependencies.Test.kotest_console)
    testImplementation(Dependencies.Test.kotest_junit)

    // fake data test library dependencies
    testImplementation(Dependencies.Test.kotlin_faker)

    // mockk test library dependencies
    testImplementation(Dependencies.Test.mockk)

    // reflections test library dependencies
    testImplementation(Dependencies.Test.reflections)

    // kotlin test library dependencies
    testImplementation(Dependencies.Test.kotlin_test_junit)

    // kotlinx test library dependencies
    implementation(Dependencies.Test.kotlinx_coroutines_test)

    // spek2 test library dependencies
    implementation(Dependencies.Test.spek2_dsl)
    implementation(Dependencies.Test.spek2_junit)

    // junit5 test library dependencies
    addJUnit5TestDependencies()
//    testRuntimeOnly(Dependencies.Test.junit_runner)
//    testRuntimeOnly(Dependencies.Test.junit_launcher)
//    testRuntimeOnly(Dependencies.Test.junit_api)
//    testRuntimeOnly(Dependencies.Test.junit_engine)
//    testRuntimeOnly(Dependencies.Test.junit_params)
//    testRuntimeOnly(Dependencies.Test.junit_vintage)
  }
}

val generateCiProfile by tasks.registering(misc.ProfileConfigTask::class) {
  fileToUpdate = file("${rootProject.rootDir}/profiles/ci.properties")
  generatedSourceOutput =
    file("${rootProject.buildDir}/generated/source/main/kotlin/io/nullables/api/profiles/ci")
}

val generateDebugProfile by tasks.registering(misc.ProfileConfigTask::class) {
  fileToUpdate = file("${rootProject.rootDir}/profiles/debug.properties")
  generatedSourceOutput =
    file("${rootProject.buildDir}/generated/source/main/kotlin/io/nullables/api/profiles/debug")
}
