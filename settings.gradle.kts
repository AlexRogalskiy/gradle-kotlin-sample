rootProject.name = "gradle-kotlin-sample"
rootProject.buildFileName = "build.gradle.kts"

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

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

include(
  "appflow",
  "testflow"
)
