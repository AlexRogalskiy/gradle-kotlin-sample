import java.io.ByteArrayOutputStream

plugins {
  java
}

fun Project.assertDefaultConfigUpToDate(defaultConfigFile: String = "$projectDir/config/detekt/detekt.yml") {
  val configDiff = ByteArrayOutputStream()

  exec {
    commandLine = listOf("git", "diff", defaultConfigFile)
    standardOutput = configDiff
  }

  if (configDiff.toString().isNotEmpty()) {
    throw GradleException(
      "The default-detekt-config.yml is not up-to-date. " +
        "You can execute the generateDocumentation Gradle task to update it and commit the changed files."
    )
  }
}
