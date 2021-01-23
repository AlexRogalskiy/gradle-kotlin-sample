package plugins

import Config
import Versions
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

apply<KtlintPlugin>()

configure<KtlintExtension> {
  version.set(Versions.KTLINT)

  debug.set(true)
  verbose.set(true)
  android.set(false)
  outputToConsole.set(true)
  outputColorName.set(Config.KTLINT_COLOR_NAME)
  ignoreFailures.set(true)
  enableExperimentalRules.set(true)
  additionalEditorconfigFile.set(file("${project.rootDir}/.editorconfig"))

  reporters {
    reporter(ReporterType.PLAIN)
    reporter(ReporterType.CHECKSTYLE)
    reporter(ReporterType.JSON)
  }

  kotlinScriptAdditionalPaths {
    include(fileTree("scripts/"))
  }

  filter {
    exclude("**/generated/**")
    include("**/kotlin/**")
  }
}
