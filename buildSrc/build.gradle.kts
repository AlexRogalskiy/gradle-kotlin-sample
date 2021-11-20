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
plugins {
  `kotlin-dsl`
  `kotlin-dsl-precompiled-script-plugins`
  `java-gradle-plugin`
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}

repositories {
  google()
  mavenCentral()
  mavenLocal() // used to publish and test local gradle plugin changes
  maven("https://plugins.gradle.org/m2/")
  gradlePluginPortal()
//  jcenter() {
//    content {
//      // detekt needs 'kotlinx-html' for the html report
//      includeGroup("org.jetbrains.kotlinx")
//    }
//  }
}

dependencies {
  implementation(kotlin("script-runtime"))

  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersions.kotlin}")
  implementation("org.jetbrains.kotlin:kotlin-serialization:${PluginVersions.kotlin}")
  implementation("org.jetbrains.dokka:dokka-gradle-plugin:${PluginVersions.dokka}")
  implementation("org.jetbrains.kotlinx:binary-compatibility-validator:${PluginVersions.compat_validator}")

  implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginVersions.detekt}")
  implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:${PluginVersions.nexus_staging}")

  implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${PluginVersions.sonarqube}")
  implementation("org.jlleitschuh.gradle:ktlint-gradle:${PluginVersions.ktlint}")

  implementation("com.adarshr:gradle-test-logger-plugin:${PluginVersions.test_logger}")
  implementation("com.diffplug.spotless:spotless-plugin-gradle:${PluginVersions.spotless}")
  implementation("com.vdurmont:semver4j:${PluginVersions.semver4j}")
  implementation("com.google.android.gms:oss-licenses-plugin:${PluginVersions.oss_license}")

  implementation("com.github.blueboxware.tocme:com.github.blueboxware.tocme.gradle.plugin:${PluginVersions.tocme}")
  implementation("com.github.breadmoirai:github-release:${PluginVersions.github_release}")
  implementation("com.github.jengelman.gradle.plugins:shadow:${PluginVersions.shadow}")
  implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersions.versions}")
}

object PluginVersions {
  const val kotlin = "1.4.30-RC"
  const val detekt = "1.19.0-RC2"
  const val github_release = "2.2.12"
  const val oss_license = "0.10.2"
  const val ktlint = "10.2.0"
  const val spotless = "5.9.0"
  const val test_logger = "2.1.1"
  const val shadow = "5.2.0"
  const val versions = "0.28.0"
  const val sonarqube = "2.8"
  const val dokka = "1.4.10"
  const val semver4j = "3.1.0"
  const val nexus_staging = "0.22.0"
  const val compat_validator = "0.3.0"
  const val tocme = "1.2"
}
