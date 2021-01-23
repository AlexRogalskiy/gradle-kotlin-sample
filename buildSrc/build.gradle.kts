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
  jcenter() {
    content {
      // detekt needs 'kotlinx-html' for the html report
      includeGroup("org.jetbrains.kotlinx")
    }
  }
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Plugins.KOTLIN_VERSION}")
  implementation("org.jetbrains.kotlin:kotlin-serialization:${Dependencies.Plugins.KOTLIN_VERSION}")
  implementation("org.jetbrains.dokka:dokka-gradle-plugin:${Dependencies.Plugins.DOKKA_VERSION}")
  implementation("org.jetbrains.kotlinx:binary-compatibility-validator:${Dependencies.Plugins.KOTLIN_API_VALIDATOR_VERSION}")

  implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Dependencies.Plugins.DETEKT_VERSION}")
  implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:${Dependencies.Plugins.NEXUS_VERSION}")

  implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Dependencies.Plugins.SONAR_VERSION}")
  implementation("org.jlleitschuh.gradle:ktlint-gradle:${Dependencies.Plugins.KTLINT_VERSION}")

  implementation("com.adarshr:gradle-test-logger-plugin:${Dependencies.Plugins.TESTLOGGER_VERSION}")
  implementation("com.diffplug.spotless:spotless-plugin-gradle:${Dependencies.Plugins.SPOTLESS_VERSION}")
  implementation("com.vdurmont:semver4j:${Dependencies.Plugins.SEMVER4J_VERSION}")
  implementation("com.google.android.gms:oss-licenses-plugin:${Dependencies.Plugins.OSS_LICENSE_VERSION}")
  implementation("com.github.breadmoirai:github-release:${Dependencies.Plugins.GITHUB_RELEASE_VERSION}")
  implementation("com.github.jengelman.gradle.plugins:shadow:${Dependencies.Plugins.SHADOW_VERSION}")
  implementation("com.github.ben-manes:gradle-versions-plugin:${Dependencies.Plugins.VERSIONS_VERSION}")
}

object Dependencies {
  object Plugins {
    const val KOTLIN_VERSION = "1.4.21"
    const val DETEKT_VERSION = "1.15.0"
    const val GITHUB_RELEASE_VERSION = "2.2.12"
    const val OSS_LICENSE_VERSION = "0.10.2"
    const val KTLINT_VERSION = "9.3.0"
    const val SPOTLESS_VERSION = "5.9.0"
    const val TESTLOGGER_VERSION = "2.1.1"
    const val SHADOW_VERSION = "5.2.0"
    const val VERSIONS_VERSION = "0.28.0"
    const val SONAR_VERSION = "2.8"
    const val DOKKA_VERSION = "1.4.10"
    const val SEMVER4J_VERSION = "3.1.0"
    const val NEXUS_VERSION = "0.22.0"
    const val KOTLIN_API_VALIDATOR_VERSION = "0.3.0"
  }
}
