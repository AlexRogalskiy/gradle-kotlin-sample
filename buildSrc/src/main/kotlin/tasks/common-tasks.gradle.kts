package tasks

import extensions.shouldTreatCompilerWarningsAsErrors
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.javaVersion
import utils.parallelForks

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
      jvmTarget = Versions.JVM_TARGET
      apiVersion = Versions.API_VERSION
      languageVersion = Versions.LANGUAGE_VERSION

      kotlinOptions.freeCompilerArgs = listOf(
        "-progressive",
        "-Xskip-runtime-version-check",
        "-Xdisable-default-scripting-plugin",
        "-Xuse-experimental=kotlin.Experimental",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xinline-classes"
      )
      kotlinOptions.allWarningsAsErrors = project.shouldTreatCompilerWarningsAsErrors()
    }
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

//    testlogger {
//      setTheme("standard-parallel")
//      setShowExceptions(true)
//      setShowStackTraces(true)
//      setShowCauses(true)
//      setShowFullStackTraces(true)
//      setShowSummary(true)
//      setShowSimpleNames(true)
//      setShowStandardStreams(true)
//      setShowPassedStandardStreams(false)
//      setShowSkippedStandardStreams(false)
//      setShowFailedStandardStreams(true)
//    }
  }
}
