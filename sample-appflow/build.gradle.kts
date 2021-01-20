apply("../publish.gradle.kts")

plugins {
  java
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  testImplementation("junit:junit:${Dependencies.Libs.JUNIT_VERSION}")
}

//tasks.withType<Jar>().configureEach {
//  dependsOn(configurations.runtimeClasspath)
//  from({
//    configurations.runtimeClasspath.get()
//      .asSequence()
//      .filterNot { "org.jetbrains" in it.toString() }
//      .filterNot { "org.intellij" in it.toString() }
//      .map { if (it.isDirectory) it else zipTree(it) }
//      .toList()
//  })
//}

object Dependencies {

  object Plugins {
    const val DETEKT_PLUGIN = "1.15.0"
  }

  object Libs {
    const val JUNIT_VERSION = "4.12"
  }
}
