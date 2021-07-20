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
import constants.Ci
import constants.Versions.project
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension

apply(plugin = "java")
apply(plugin = "java-library")
apply(plugin = "maven-publish")
apply(plugin = "signing")

repositories {
  mavenCentral()
}

val signingKey: String? by project
val signingPassword: String? by project

fun Project.publishing(action: PublishingExtension.() -> Unit) =
  configure(action)

fun Project.signing(configure: SigningExtension.() -> Unit): Unit =
  configure(configure)

fun Project.java(configure: JavaPluginExtension.() -> Unit): Unit =
  configure(configure)


val publications: PublicationContainer =
  (extensions.getByName("publishing") as PublishingExtension).publications

signing {
  useGpgCmd()
  if (signingKey != null && signingPassword != null) {
    @Suppress("UnstableApiUsage")
    useInMemoryPgpKeys(signingKey, signingPassword)
  }
  if (Ci.isRelease) {
    sign(publications)
  }
}

java {
  withJavadocJar()
  withSourcesJar()
}

publishing {
  repositories {
    maven {
      val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
      val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
      name = "deploy"
      url = if (Ci.isRelease) releasesRepoUrl else snapshotsRepoUrl
      credentials {
        username = java.lang.System.getenv("OSSRH_USERNAME") ?: ""
        password = java.lang.System.getenv("OSSRH_PASSWORD") ?: ""
      }
    }
  }

  publications {
    register("mavenJava", MavenPublication::class) {
      from(components["java"])
      pom {
        name.set("gradle-kotlin-sample")
        description.set("Configuration for Kotlin")
        url.set("http://www.github.com/AlexRogalskiy/gradle-kotlin-sample")

        scm {
          connection.set("scm:git:http://www.github.com/AlexRogalskiy/gradle-kotlin-sample/")
          developerConnection.set("scm:git:http://github.com/AlexRogalskiy/")
          url.set("http://www.github.com/AlexRogalskiy/gradle-kotlin-sample/")
        }

        licenses {
          license {
            name.set("The Apache 2.0 License")
            url.set("https://opensource.org/licenses/Apache-2.0")
          }
        }

        developers {
          developer {
            id.set("AlexRogalskiy")
            name.set("Alexander Rogalskiy")
            email.set("email@mail.ru")
          }
        }
      }
    }

//    register("sonatype", MavenPublication::class) {
//      artifactId = "smartype-generator"
//      artifact(fatJar)
//      artifact(tasks["javadocJar"])
//      artifact(tasks["sourcesJar"])
//      pom {
//        name.set("Smartype Generator")
//        description.set("Generator ")
//        url.set("https://github.com/mParticle/smartype")
//        licenses {
//          license {
//            name.set("The Apache License, Version 2.0")
//            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//          }
//        }
//        developers {
//          developer {
//            id.set("samdozor")
//            name.set("Sam Dozor")
//            email.set("sdozor@mparticle.com")
//          }
//          developer {
//            id.set("peterjenkins")
//            name.set("Peter Jenkins")
//            email.set("pjenkins@mparticle.com")
//          }
//        }
//        scm {
//          connection.set("scm:git:git://github.com/mParticle/smartype.git")
//          developerConnection.set("scm:git:ssh://github.com/mParticle/smartype.git")
//          url.set("https://github.com/mParticle/smartype")
//        }
//      }
//    }
  }
}
