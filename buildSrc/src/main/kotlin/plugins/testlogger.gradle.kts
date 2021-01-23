package plugins

import com.adarshr.gradle.testlogger.TestLoggerPlugin
import com.adarshr.gradle.testlogger.TestLoggerExtension

apply<TestLoggerPlugin>()

configure<TestLoggerExtension> {
  setTheme("mocha")
  setSlowThreshold(5000)
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
