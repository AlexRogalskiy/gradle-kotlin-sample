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
package tasks

import extensions.shouldTreatCompilerWarningsAsErrors
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.javaVersion
import utils.kotlinVersion
import utils.parallelForks
import extensions.getProjectGroup
import extensions.getProjectVersion
import extensions.getProjectDescription

plugins {
  id("org.jetbrains.kotlin.jvm") apply false
  id("org.jetbrains.kotlin.kapt") apply false
  id("maven") apply false
  id("java") apply false
}

configurations {
  "implementation" {
    resolutionStrategy.failOnVersionConflict()
  }
}

java {
  sourceSets {
    map { it.java.srcDir("src/${it.name}/kotlin") }
  }
}

configure<SourceSetContainer> {
  named("main") {
    java.srcDir("src/core/java")
  }
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

//configure<kotlinx.validation.ApiValidationExtension> {
//  /**
//   * Packages that are excluded from public API dumps even if they
//   * contain public API.
//   */
//  ignoredPackages.add("kotlinx.coroutines.internal")
//  /**
//   * Sub-projects that are excluded from API validation
//   */
//  ignoredProjects.addAll(listOf("testflow"))
//  /**
//   * Flag to programmatically disable compatibility validator
//   */
//  validationDisabled = false
//}

// additional source sets
sourceSets {
  val examples by creating {
    java {
      compileClasspath += sourceSets.main.get().output
      runtimeClasspath += sourceSets.main.get().output
    }
  }
}

tasks {
  withType<JavaCompile> {
    options.isIncremental = true
    allprojects {
      options.compilerArgs.addAll(
        arrayOf(
          "-Xlint:-unchecked",
          "-Xlint:deprecation",
          "-Xdiags:verbose"
        )
      )
    }
  }

  withType<KotlinCompile> {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()

    kotlinOptions {
      jvmTarget = javaVersion.toString()
      apiVersion = kotlinVersion.toString()
      languageVersion = kotlinVersion.toString()

      kotlinOptions.freeCompilerArgs = listOf(
        "-progressive",
        "-Xskip-runtime-version-check",
        "-Xdisable-default-scripting-plugin",
        "-Xuse-experimental=kotlin.Experimental",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xinline-classes"
      )
      kotlinOptions.allWarningsAsErrors = project.shouldTreatCompilerWarningsAsErrors()
    }
  }

  withType<Test> {
    useJUnitPlatform()

    systemProperty("spek2.jvm.cg.scan.concurrency", 1) // use one thread for classpath scanning
    systemProperty("spek2.execution.test.timeout", 0) // disable test timeout
    systemProperty("spek2.discovery.parallel.enabled", 0) // disable parallel test discovery
    val compileSnippetText: Boolean = if (project.hasProperty("compile-test-snippets")) {
      (project.property("compile-test-snippets") as String).toBoolean()
    } else {
      false
    }
    systemProperty("compile-snippet-tests", compileSnippetText)

    testLogging {
      // set options for log level LIFECYCLE
      events = setOf(
        TestLogEvent.FAILED,
        TestLogEvent.STARTED,
        TestLogEvent.PASSED,
        TestLogEvent.SKIPPED,
        TestLogEvent.STANDARD_OUT
      )
      exceptionFormat = TestExceptionFormat.FULL
      showStandardStreams = true
      showExceptions = true
      showCauses = true
      showStackTraces = true
    }

    maxParallelForks = parallelForks

    filter {
      isFailOnNoMatchingTests = false
    }
  }

  withType<Jar> {
    archiveClassifier.set("uber")

    manifest {
//      attributes["Class-Path"] =
//        configurations.compileClasspath.get().map {
//          it.getPath()
//        }.joinToString(" ")
      attributes["Project-Version"] = getProjectVersion()
      attributes["Project-Group"] = getProjectGroup()
      attributes["Project-Description"] = getProjectDescription()
    }
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from({
      exclude("META-INF/LICENSE.txt")
      exclude("META-INF/NOTICE.txt")
      configurations.runtimeClasspath.get().map {
        if (it.isDirectory) {
          it
        } else {
          zipTree(it)
        }
      }
    })
  }

  registering(Delete::class) {
    delete(rootProject.buildDir)
  }
}
