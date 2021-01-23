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

plugins {
  id("idea") apply false
}

idea {
  module {
    sourceDirs.plusAssign(
      files(
        "$buildDir/generated/source/kapt/main",
        "$buildDir/generated/source/kapt/debug",
        "$buildDir/generated/source/kapt/release",
        "$buildDir/generated/source/kaptKotlin/main",
        "$buildDir/generated/source/kaptKotlin/debug",
        "$buildDir/generated/source/kaptKotlin/release",
        "$buildDir/tmp/kapt/main/kotlinGenerated"
      )
    )
    generatedSourceDirs.plusAssign(
      files(
        "$buildDir/generated/source/kapt/main",
        "$buildDir/generated/source/kapt/debug",
        "$buildDir/generated/source/kapt/release",
        "$buildDir/generated/source/kaptKotlin/main",
        "$buildDir/generated/source/kaptKotlin/debug",
        "$buildDir/generated/source/kaptKotlin/release",
        "$buildDir/tmp/kapt/main/kotlinGenerated"
      )
    )
  }
}
