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
  maven("https://oss.jfrog.org/artifactory/oss-snapshot-local/")

  maven("https://plugins.gradle.org/m2/")
  maven("https://kotlin.bintray.com/kotlinx")

  maven("https://dl.bintray.com/serpro69/maven/")
  maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
  maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

plugins {
  id("org.jetbrains.kotlin.jvm")
  id("org.jetbrains.kotlin.kapt")

  id("org.jetbrains.dokka") apply false
  id("com.github.johnrengelman.shadow") apply false
  id("com.diffplug.spotless") version "5.9.0"
  id("com.adarshr.test-logger") version "2.1.1"
  id("org.jlleitschuh.gradle.ktlint") version "9.3.0"
  id("com.github.ben-manes.versions")
  id("io.gitlab.arturbosch.detekt")
  id("binary-compatibility-validator")

  id("org.sonarqube")
  id("jacoco")
  id("maven")
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

// additional source sets
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
  debug = false
  failFast = true
  parallel = true
  ignoreFailures = false
  buildUponDefaultConfig = true
  disableDefaultRuleSets = false

  toolVersion = Dependencies.Libs.DETEKT_VERSION
  input = files("src/main/kotlin", "src/test/kotlin")

  config =
    files("${rootProject.rootDir}/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
  baseline =
    file("${rootProject.rootDir}/config/baseline.xml") // a way of suppressing issues before introducing detekt

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

allprojects {
  apply(plugin = "java")
  apply(plugin = "com.diffplug.spotless")
  apply(plugin = "com.adarshr.test-logger")
  apply(plugin = "org.jlleitschuh.gradle.ktlint")

  spotless {
    kotlin {
      ktlint()
    }
//    kotlinGradle {
//      ktlint()
//    }
  }

  testlogger {
    setTheme("mocha")
    setSlowThreshold(5000)
  }

  ktlint {
    // debug.set(true)
    // verbose.set(true)
    version.set(Versions.KTLINT)
    enableExperimentalRules.set(true)
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
    implementation(kotlin("stdlib"))

    // annotation processors
    kapt("io.arrow-kt:arrow-meta:${Dependencies.Libs.ARROW_VERSION}")
    kaptTest("io.arrow-kt:arrow-meta:${Dependencies.Libs.ARROW_VERSION}")

    // command line args parsing library dependencies
    implementation("com.github.ajalt:clikt:${Dependencies.Libs.CLICKT_VERSION}")

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

    // json parsing library dependencies
    implementation("com.beust:klaxon:${Dependencies.Libs.KLAXON_VERSION}")

    // logging library dependencies
    implementation("ch.qos.logback:logback-classic:${Dependencies.Libs.LOGBACK_VERSION}")

    // rxjava library dependencies
    implementation("io.reactivex.rxjava2:rxjava:${Dependencies.Libs.RXJAVA_VERSION}")

    // kotlinx library dependencies
    implementation(
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:" +
        "${Dependencies.Libs.KOTLINX_COROUTINES_VERSION}"
    )

    // kotest library dependencies
    testImplementation("io.kotest:kotest-assertions-arrow-jvm:${Dependencies.Libs.KOTEST_VERSION}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${Dependencies.Libs.KOTEST_VERSION}")
    testImplementation("io.kotest:kotest-property-jvm:${Dependencies.Libs.KOTEST_VERSION}")
    testImplementation(
      "io.kotest:kotest-runner-console-jvm:" +
        "${Dependencies.Libs.KOTEST_CONSOLE_VERSION}"
    )
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${Dependencies.Libs.KOTEST_VERSION}")

    // fake data libary dependencies
    testImplementation("io.github.serpro69:kotlin-faker:${Dependencies.Libs.KOTLIN_FAKER_VERSION}")

    // junit5 library dependencies
    testImplementation(
      "io.kotlintest:kotlintest-runner-junit5:" +
        "${Dependencies.Libs.KOTLIN_TEST_VERSION}"
    )
    testRuntimeOnly(
      "org.junit.vintage:junit-vintage-engine:" +
        "${Dependencies.Libs.JUNIT_VINTAGE_VERSION}"
    )
  }

  tasks.named<Test>("test") {
    useJUnitPlatform()

    testlogger {
      setTheme("standard-parallel")
      setShowExceptions(true)
      setShowStackTraces(true)
      setShowCauses(true)
      setShowFullStackTraces(true)
      setShowSummary(true)
      setShowSimpleNames(true)
      setShowStandardStreams(true)
      setShowPassedStandardStreams(false)
      setShowSkippedStandardStreams(false)
      setShowFailedStandardStreams(true)
    }

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

  tasks.withType<Jar>().configureEach {
    archiveClassifier.set("uber")

    manifest {
      // attributes["Main-Class"] = application.mainClassName
      attributes["Class-Path"] =
        configurations.compileClasspath.get().map {
          it.getPath()
        }.joinToString(" ")
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

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()

    kotlinOptions {
      jvmTarget = Versions.JVM_TARGET
      apiVersion = Versions.API_VERSION
      languageVersion = Versions.LANGUAGE_VERSION
      freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
      freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
    }
  }

  tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = Versions.JVM_TARGET
  }

  tasks.withType<JacocoReport> {
    reports {
      xml.isEnabled = true
      html.isEnabled = true
    }
  }
}

tasks {
  check {
    dependsOn(jacocoTestReport)
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
    const val LOGBACK_VERSION = "1.2.3"
    const val KLAXON_VERSION = "5.4"
    const val CLICKT_VERSION = "2.6.0"
    const val KTLINT_VERSION = "0.31.0"
    const val KOTLIN_TEST_VERSION = "3.4.2"
    const val KOTLINX_COROUTINES_VERSION = "1.4.2"
    const val JUNIT_VINTAGE_VERSION = "5.7.0"
    const val KOTEST_VERSION = "4.3.2"
    const val KOTEST_CONSOLE_VERSION = "4.1.3.2"
    const val KOTLIN_FAKER_VERSION = "1.6.0"
  }
}
