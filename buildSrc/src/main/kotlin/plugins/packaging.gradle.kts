/*
 * Copyright (C) 2021. Alexander Rogalskiy. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package plugins

import constants.Ci
import constants.Constants
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.signing.SigningExtension

plugins {
  java apply false
  `java-library` apply false
  `maven-publish` apply false
  signing apply false
  id("io.codearte.nexus-staging") apply false
}

val signingKey: String? by project
val signingPassword: String? by project

fun Project.java(configure: JavaPluginExtension.() -> Unit): Unit =
  configure(configure)

fun Project.publishing(action: PublishingExtension.() -> Unit) =
  configure(action)

fun Project.signing(configure: SigningExtension.() -> Unit): Unit =
  configure(configure)

val sonatypeUsername: String? = findProperty("sonatypeUsername")
  ?.toString()
  ?: System.getenv("MAVEN_CENTRAL_USER")
val sonatypePassword: String? = findProperty("sonatypePassword")
  ?.toString()
  ?: System.getenv("MAVEN_CENTRAL_PW")

nexusStaging {
  packageGroup = "io.nullables.api.sample"
  stagingProfileId = ""
  username = sonatypeUsername
  password = sonatypePassword
}

subprojects {
  apply {
    plugin("maven-publish")
    plugin("signing")
  }

  java {
    withJavadocJar()
    withSourcesJar()
  }

  publishing {
    repositories {
      maven {
        name = "mavenCentral"
        url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
        credentials {
          username = sonatypeUsername
          password = sonatypePassword
        }
      }

      maven {
        name = "sonatypeSnapshot"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        credentials {
          username = sonatypeUsername
          password = sonatypePassword
        }
      }
    }

    publications.register<MavenPublication>(Constants.DETEKT_PUBLICATION) {
      groupId = project.group as? String
      artifactId = project.name
      version = project.version as? String

      pom {
        name.set("gradle-kotlin-sample")
        description.set("Gradle Kotlin sample project")
        url.set("http://www.github.com/AlexRogalskiy/gradle-kotlin-sample")

        licenses {
          license {
            name.set("The Apache Software License, Version 2.0")
            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            distribution.set("repo")
          }
        }

        developers {
          developer {
            id.set("AlexRogalskiy")
            name.set("Alexander Rogalskiy")
            email.set("test@gmail.com")
          }
        }

        scm {
          connection.set("scm:git:http://www.github.com/AlexRogalskiy/gradle-kotlin-sample")
          developerConnection.set("scm:git:http://github.com/AlexRogalskiy/gradle-kotlin-sample")
          url.set("https://github.com/AlexRogalskiy/gradle-kotlin-sample")
        }
      }
    }
  }

  if (findProperty("signing.keyId") != null) {
    signing {
      useGpgCmd()
      if (signingKey != null && signingPassword != null) {
        @Suppress("UnstableApiUsage")
        useInMemoryPgpKeys(signingKey, signingPassword)
      }
      if (Ci.isRelease) {
        sign(publishing.publications[Constants.DETEKT_PUBLICATION])
      }
    }
  } else {
    logger.info("Signing Disabled as the PGP key was not found")
  }
}
