import com.github.breadmoirai.githubreleaseplugin.GithubReleaseTask
import com.vdurmont.semver4j.Semver

plugins {
    id("com.github.breadmoirai.github-release")
}

githubRelease {
    token(project.findProperty("github.token") as? String ?: "")
    owner.set("detekt")
    repo.set("detekt")
    overwrite.set(true)
    dryRun.set(false)

    body {
        var changelog = project.file("docs/pages/changelog 1.x.x.md").readText()
        val nextNonBetaVersion = project.version.toString().substringBeforeLast("-")
        val sectionStart = "#### $nextNonBetaVersion"
        changelog = changelog.substring(changelog.indexOf(sectionStart) + sectionStart.length)
        changelog = changelog.substring(0, changelog.indexOf("#### 1."))
        changelog.trim()
    }

    val cliBuildDir = project(":detekt-cli").buildDir
    releaseAssets.setFrom(
        files(
            cliBuildDir.resolve("libs/detekt-cli-${project.version}-all.jar"),
            cliBuildDir.resolve("distributions/detekt-cli-${project.version}.zip"),
            cliBuildDir.resolve("run/detekt"),
            project(":detekt-formatting").buildDir.resolve("libs/detekt-formatting-${project.version}.jar")
        )
    )
}

tasks.withType<GithubReleaseTask>().configureEach {
    dependsOn(":detekt-cli:shadowJarExecutable")
}

fun updateVersion(increment: (Semver) -> Semver) {
    val versionsFile = file("${rootProject.rootDir}/buildSrc/src/main/kotlin/Versions.kt")
    val newContent = versionsFile.readLines()
        .joinToString("\n") {
            if (it.contains("const val DETEKT: String")) {
                val oldVersion = it.substringAfter("\"").substringBefore("\"")
                val newVersion = Semver(oldVersion).let(increment)
                println("Next release: $newVersion")
                """    const val DETEKT: String = "$newVersion""""
            } else {
                it
            }
        }
    versionsFile.writeText("$newContent\n")
}

val incrementPatch by tasks.registering { doLast { updateVersion { it.nextPatch() } } }
val incrementMinor by tasks.registering { doLast { updateVersion { it.nextMinor() } } }
val incrementMajor by tasks.registering { doLast { updateVersion { it.nextMajor() } } }

val applyDocVersion by tasks.registering(UpdateVersionInFileTask::class) {
    fileToUpdate = file("${rootProject.rootDir}/docs/_config.yml")
    linePartToFind = "project_version:"
    lineTransformation = { "project_version: ${Versions.PROJECT_VERSION}" }
}

val applySelfAnalysisVersion by tasks.registering(UpdateVersionInFileTask::class) {
    fileToUpdate = file("${rootProject.rootDir}/buildSrc/build.gradle.kts")
    linePartToFind = "const val PROJECT_VERSION ="
    lineTransformation = { """    const val PROJECT_VERSION = "${Versions.PROJECT_VERSION}"""" }
}
