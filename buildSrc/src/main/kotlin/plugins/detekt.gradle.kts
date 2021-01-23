package plugins

import Versions
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.detekt
import utils.javaVersion

apply<DetektPlugin>()

detekt {
  debug = false
  failFast = true
  parallel = true
  ignoreFailures = false
  buildUponDefaultConfig = true
  disableDefaultRuleSets = false

  toolVersion = Versions.DETEKT_VERSION
  input = files("src/main/kotlin", "src/test/kotlin", "src/main/java", "src/test/java")

  config =
    files("${project.rootDir}/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
  baseline =
    file("${project.rootDir}/config/baseline.xml") // a way of suppressing issues before introducing detekt

  reports {
    xml {
      enabled = true
      destination = file("${project.buildDir}/reports/detekt/detekt-report.xml")
    }
    html {
      enabled = true
      destination = file("${project.buildDir}/reports/detekt/detekt-report.html")
    }
    txt {
      enabled = true
      destination = file("${project.buildDir}/reports/detekt/detekt-report.txt")
    }
    sarif {
      enabled = true
      destination = file("${project.buildDir}/reports/detekt/detekt-report.sarif")
    }
  }
}

tasks {
  withType<Detekt> {
    include("**/*.kt", "**/*.kts")
    exclude("**/build/**", ".*/resources/.*", ".*test.*,.*/resources/.*,.*/tmp/.*")
    // Target version of the generated JVM bytecode. It is used for type resolution.
    jvmTarget = javaVersion.toString()
  }
}
