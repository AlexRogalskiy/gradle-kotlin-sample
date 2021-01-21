repositories {
  mavenCentral()
  mavenLocal()
  google()
  jcenter {
    content {
      // detekt needs 'kotlinx-html' for the html report
      includeGroup("org.jetbrains.kotlinx")
    }
  }

  maven("https://oss.sonatype.org/content/repositories/snapshots/")
  maven("https://plugins.gradle.org/m2/")
  maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
  maven("https://kotlin.bintray.com/kotlinx")
}

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.kapt")

  id("org.jetbrains.dokka") apply false
  id("com.github.johnrengelman.shadow") apply false
  id("com.github.ben-manes.versions")

  id("org.sonarqube")

  id("binary-compatibility-validator")
  id("io.gitlab.arturbosch.detekt")

  id("maven")
  id("idea")
}

idea {
  module {
    sourceDirs.plusAssign(
      files(
        "$buildDir/generated/source/kapt/main",
        "$buildDir/generated/source/kapt/debug",
        "$buildDir/generated/source/kapt/release",
        "$buildDir/generated/source/kaptKotlin/main",
        "$buildDir/generated/source/kaptKotlin/debug",
        "$buildDir/generated/source/kaptKotlin/release",
        "$buildDir/tmp/kapt/main/kotlinGenerated"
      )
    )
    generatedSourceDirs.plusAssign(
      files(
        "$buildDir/generated/source/kapt/main",
        "$buildDir/generated/source/kapt/debug",
        "$buildDir/generated/source/kapt/release",
        "$buildDir/generated/source/kaptKotlin/main",
        "$buildDir/generated/source/kaptKotlin/debug",
        "$buildDir/generated/source/kaptKotlin/release",
        "$buildDir/tmp/kapt/main/kotlinGenerated"
      )
    )
  }
}

configurations {
  "implementation" {
    resolutionStrategy.failOnVersionConflict()
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

configure<kotlinx.validation.ApiValidationExtension> {
  /**
   * Packages that are excluded from public API dumps even if they
   * contain public API.
   */
  ignoredPackages.add("kotlinx.coroutines.internal")
  /**
   * Sub-projects that are excluded from API validation
   */
  ignoredProjects.addAll(listOf("testflow"))
  /**
   * Flag to programmatically disable compatibility validator
   */
  validationDisabled = false
}

//additional source sets
sourceSets {
  val examples by creating {
    java {
      compileClasspath += sourceSets.main.get().output
      runtimeClasspath += sourceSets.main.get().output
    }
  }
}

buildScan {
  termsOfServiceUrl = "https://gradle.com/terms-of-service"
  termsOfServiceAgree = "yes"
}

detekt {
  failFast = true
  parallel = true
  ignoreFailures = false
  buildUponDefaultConfig = true
  disableDefaultRuleSets = false
  toolVersion = Dependencies.Libs.DETEKT_VERSION
  config =
    files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
  baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt

  reports {
    xml {
      enabled = true
      destination = file("$buildDir/reports/build.xml")
    }
    html {
      enabled = true
      destination = file("$buildDir/reports/build.html")
    }
    txt {
      enabled = true
      destination = file("$buildDir/reports/build.txt")
    }
    sarif {
      enabled = true
      destination = file("$$buildDir/reports/detekt.sarif")
    }
  }
}

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply(plugin = "kotlin-kapt")

  group = "io.nullables.api.sample"
  version = "1.0.0-SNAPSHOT"
  description = "Gradle kotlin sample project"

  repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
  }

  dependencies {
    // kotlin library dependencies
    kotlin(module = "jvm", version = "1.4.71")
    implementation(kotlin("stdlib-jdk8"))

    // annotation processors
    kapt("io.arrow-kt:arrow-meta:${Dependencies.Libs.ARROW_VERSION}")
    kaptTest("io.arrow-kt:arrow-meta:${Dependencies.Libs.ARROW_VERSION}")

    // arrow library dependencies
    implementation("io.arrow-kt:arrow-annotations:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-core:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-fx:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-fx-rx2:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-optics:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-ui:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-validation:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-mtl:${Dependencies.Libs.ARROW_VERSION}")
    implementation("io.arrow-kt:arrow-syntax:${Dependencies.Libs.ARROW_VERSION}")

    // rxjava library dependencies
    implementation("io.reactivex.rxjava2:rxjava:${Dependencies.Libs.DETEKT_VERSION}")
    // kotlinx library dependencies
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependencies.Libs.KOTLINX_COROUTINES_VERSION}")
    // kotest library dependencies
    testImplementation("io.kotest:kotest-assertions-core-jvm:${Dependencies.Libs.KOTEST_VERSION}")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${Dependencies.Libs.KOTEST_VERSION}")
    // junit5 library dependencies
    testImplementation("io.kotlintest:kotlintest-runner-junit5:${Dependencies.Libs.KOTLIN_TEST_VERSION}")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:${Dependencies.Libs.JUNIT_VINTAGE_VERSION}")
  }

  tasks.named<Test>("test") {
    useJUnitPlatform()

    filter {
      isFailOnNoMatchingTests = false
    }

    testLogging {
      showExceptions = true
      showStandardStreams = true
      exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
      events = setOf(
        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
      )
    }
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()

    kotlinOptions {
      jvmTarget = Versions.JVM_TARGET
      apiVersion = Versions.API_VERSION
      languageVersion = Versions.LANGUAGE_VERSION
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
  }

  tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = Versions.JVM_TARGET
  }
}

object Dependencies {
  object Plugins {
    const val DETEKT_PLUGIN = "1.15.0"
  }

  object Libs {
    const val RXJAVA_VERSION = "2.2.20"
    const val DETEKT_VERSION = "1.15.0"
    const val ARROW_VERSION = "0.11.0"
    const val KTLINT_VERSION = "0.31.0"
    const val KOTLIN_TEST_VERSION = "3.4.2"
    const val KOTLINX_COROUTINES_VERSION = "1.4.2"
    const val JUNIT_VINTAGE_VERSION = "5.7.0"
    const val KOTEST_VERSION = "4.3.1"
  }
}
