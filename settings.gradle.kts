enableFeaturePreview("GRADLE_METADATA")

rootProject.name = "io.nullables.api.sample"

include(
  "sample-appflow",
  "sample-testflow"
)

pluginManagement {
  repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2/")
    gradlePluginPortal()
    jcenter()
  }
}

// build scan plugin can only be applied in settings file
plugins {
  id("com.gradle.enterprise") version "3.3.1"
}
