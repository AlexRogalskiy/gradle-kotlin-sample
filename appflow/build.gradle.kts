//apply("../publish.gradle.kts")

plugins {
  java
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:${Dependencies.Libs.JUNIT_VERSION}")
}

object Dependencies {
  object Libs {
    const val JUNIT_VERSION = "1.4.30-RC"
  }
}
