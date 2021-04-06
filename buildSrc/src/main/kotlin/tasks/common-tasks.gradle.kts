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

import extensions.createKotlinMainSources
import extensions.createKotlinTestSources
import extensions.shouldTreatCompilerWarningsAsErrors
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.javaVersion
import utils.kotlinVersion
import utils.parallelForks

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

kapt {
  useBuildCache = true
}

configure<SourceSetContainer> {
  named("main") {
    java.srcDir("src/core/java")
  }
}

configure<JavaPluginConvention> {
  sourceCompatibility = javaVersion
  targetCompatibility = javaVersion
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

sourceSets {
  createKotlinMainSources(this)
  createKotlinTestSources(this)

  val examples by creating {
    java {
      compileClasspath += sourceSets.main.get().output
      runtimeClasspath += sourceSets.main.get().output
    }
  }
}

//val sourcesJar by tasks.registering(Jar::class) {
//  dependsOn(tasks.classes)
//  archiveClassifier.set("sources")
//  from(sourceSets.main.get().allSource)
//}
//
//val javadocJar by tasks.registering(Jar::class) {
//  from(tasks.javadoc)
//  archiveClassifier.set("javadoc")
//}
//
//artifacts {
//  archives(sourcesJar)
//  archives(javadocJar)
//}
//
//publishing {
//  publications.named<MavenPublication>(Constants.DETEKT_PUBLICATION) {
//    from(components["java"])
//    artifact(sourcesJar.get())
//    artifact(javadocJar.get())
//  }
//}

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
      allWarningsAsErrors = project.shouldTreatCompilerWarningsAsErrors()

      freeCompilerArgs = listOf(
        "-progressive",
        "-Xuse-ir",
        "-Xjvm-default=enable",
        "-Xskip-runtime-version-check",
        "-Xskip-prerelease-check",
        "-Xdisable-default-scripting-plugin",
        "-Xuse-experimental=kotlin.Experimental",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.time.ExperimentalTime",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xinline-classes"
      )
    }
  }

  withType<Javadoc> {
    (options as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
    options.encoding = "UTF-8"
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

    val copyTestResources by registering(Copy::class) {
      from("${projectDir}/src/test/resources")
      into("${buildDir}/classes/kotlin/test")
    }

    processTestResources.configure {
      dependsOn(copyTestResources)
    }
  }

  withType<Jar> {
    archiveClassifier.set("uber")
    dependsOn(configurations.runtimeClasspath)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
//      attributes["Class-Path"] =
//        configurations.compileClasspath.get()
//          .asSequence()
//          .filterNot { "org.jetbrains" in it.toString() }
//          .filterNot { "org.intellij" in it.toString() }
//          .map { if (it.isDirectory) it else zipTree(it) }
//          .toList()
//          .joinToString(" ")
      attributes["Implementation-Title"] = project.extra["appTitle"]
      attributes["Implementation-Version"] = project.extra["appVersion"]
      attributes["Implementation-Group"] = project.extra["appGroup"]
      attributes["Implementation-Description"] = project.extra["appDescription"]
    }

    from(sourceSets.main.get().output)
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

  create<Zip>("zip") {
    description = "Archives sources in to a zip file"
    group = "Archive"

    from("src")
    setArchiveName("gradle-kotlin-sample.zip")
  }

  registering(Delete::class) {
    delete(allprojects.map { it.buildDir })
    delete(File("buildSrc\\build"))
//    delete(rootProject.buildDir)
  }

  val packageFat by creating(Zip::class) {
    from(compileKotlin)
    from(processResources)

    into("lib") { from(configurations.runtimeClasspath) }

    dirMode = 0b111101101 // 0755
    fileMode = 0b111101101 // 0755
  }

  val packageLibs by creating(Zip::class) {
    into("java/lib") { from(configurations.runtimeClasspath) }

    dirMode = 0b111101101 // 0755
    fileMode = 0b111101101 // 0755
  }

  val packageSkinny by creating(Zip::class) {
    from(compileKotlin)
    from(processResources)
  }

  build { dependsOn(packageSkinny) }
}
