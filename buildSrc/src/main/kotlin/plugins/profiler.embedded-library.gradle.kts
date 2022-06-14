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

// These projects are packaged into the Gradle profiler Jar, so let's make them reproducible
tasks.withType<Jar>().configureEach {
  manifest {
    attributes(
      "Implementation-Title" to "Gradle Profiler",
      "Implementation-Version" to project.version
    )
  }

  isReproducibleFileOrder = true
  isPreserveFileTimestamps = false
  dirMode = Integer.parseInt("0755", 8)
  fileMode = Integer.parseInt("0644", 8)
}
