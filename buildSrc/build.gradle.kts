plugins {
  `kotlin-dsl`
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}

repositories {
  mavenLocal() // used to publish and test local gradle plugin changes
  gradlePluginPortal()
  jcenter()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Plugins.KOTLIN_VERSION}")
  implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Dependencies.Plugins.DETEKT_VERSION}")
  implementation("com.github.breadmoirai:github-release:${Dependencies.Plugins.GITHUB_RELEASE_VERSION}")
  implementation("com.github.jengelman.gradle.plugins:shadow:${Dependencies.Plugins.SHADOW_VERSION}")
  implementation("com.github.ben-manes:gradle-versions-plugin:${Dependencies.Plugins.VERSIONS_VERSION}")
  implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${Dependencies.Plugins.SONAR_VERSION}")
  implementation("org.jetbrains.dokka:dokka-gradle-plugin:${Dependencies.Plugins.DOKKA_VERSION}")
  implementation("com.vdurmont:semver4j:${Dependencies.Plugins.SEMVER4J_VERSION}")
  implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:${Dependencies.Plugins.NEXUS_VERSION}")
  implementation("org.jetbrains.kotlinx:binary-compatibility-validator:${Dependencies.Plugins.KOTLIN_API_VALIDATOR_VERSION}")
}

object Dependencies {
  object Plugins {
    const val KOTLIN_VERSION = "1.4.21"
    const val DETEKT_VERSION = "1.15.0"
    const val GITHUB_RELEASE_VERSION = "2.2.12"
    const val SHADOW_VERSION = "5.2.0"
    const val VERSIONS_VERSION = "0.28.0"
    const val SONAR_VERSION = "2.8"
    const val DOKKA_VERSION = "1.4.10"
    const val SEMVER4J_VERSION = "3.1.0"
    const val NEXUS_VERSION = "0.22.0"
    const val KOTLIN_API_VALIDATOR_VERSION = "0.3.0"
  }
}
