plugins {
  id("idea")
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
