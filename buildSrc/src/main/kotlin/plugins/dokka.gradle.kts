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

import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask
import utils.javaVersion

apply<DokkaPlugin>()

tasks {
  withType<DokkaTask> {
    // custom output directory
    outputDirectory.set(buildDir.resolve("dokka"))
    // Set module name displayed in the final output
    moduleName.set("moduleName")
    // Use default or set to custom path to cache directory
    // to enable package-list caching
    // When this is set to default, caches are stored in $USER_HOME/.cache/dokka
    cacheRoot.set(file("default"))
  }
}
