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

//import constants.Versions
//import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
//import io.gitlab.arturbosch.detekt.detekt
//import utils.javaVersion

apply<DetektPlugin>()

//detekt {
//  debug = false
//  failFast = true
//  parallel = true
//  ignoreFailures = false
//  buildUponDefaultConfig = true
//  disableDefaultRuleSets = false
//
//  toolVersion = Versions.detekt
//  input = files("src/main/kotlin", "src/test/kotlin", "src/main/java", "src/test/java")
//
//  config =
//    files("${project.rootDir}/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
//  baseline =
//    file("${project.rootDir}/config/baseline.xml") // a way of suppressing issues before introducing detekt
//
//  reports {
//    xml {
//      enabled = true
//      destination = file("${project.buildDir}/reports/detekt/detekt-report.xml")
//    }
//    html {
//      enabled = true
//      destination = file("${project.buildDir}/reports/detekt/detekt-report.html")
//    }
//    txt {
//      enabled = true
//      destination = file("${project.buildDir}/reports/detekt/detekt-report.txt")
//    }
//    sarif {
//      enabled = true
//      destination = file("${project.buildDir}/reports/detekt/detekt-report.sarif")
//    }
//  }
//}
//
//tasks {
//  withType<Detekt> {
//    include("**/*.kt", "**/*.kts")
//    exclude("**/build/**", ".*/resources/.*", ".*test.*,.*/resources/.*,.*/tmp/.*")
//    // Target version of the generated JVM bytecode. It is used for type resolution.
//    jvmTarget = javaVersion.toString()
//  }
//}
