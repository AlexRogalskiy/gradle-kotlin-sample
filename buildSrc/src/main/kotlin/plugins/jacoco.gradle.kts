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

import constants.Versions

plugins {
  kotlin("jvm") apply false
  jacoco apply false
}

//// additional source sets
//sourceSets {
//  val examples2 by creating {
//    java {
//      compileClasspath += sourceSets.main.get().output
//      runtimeClasspath += sourceSets.main.get().output
//    }
//  }
//}

jacoco {
  toolVersion = Versions.jacoco
  //includeNoLocationClasses = true
}

val examplesOrTestUtils = setOf(
  "testflow"
)

tasks {
  withType<JacocoReport> {
    reports {
      xml.isEnabled = true
      html.isEnabled = true
    }
  }

  jacocoTestReport {
    executionData.setFrom(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))

    subprojects
      .filterNot { it.name in examplesOrTestUtils }
      .forEach {
        this@jacocoTestReport.sourceSets(it.sourceSets.main.get())
        this@jacocoTestReport.dependsOn(it.tasks.test)
      }

    reports {
      xml.isEnabled = true
      xml.destination = file("$buildDir/reports/jacoco/report.xml")
    }
  }

  check {
    dependsOn(jacocoTestReport)
  }
}
