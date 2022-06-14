#!/bin/bash

set -e -x -u

#setup gradle enterprise
mkdir -p ~/.gradle
echo "kotlin.build.scan.url=https://ge.jetbrains.com/" >> ~/.gradle/gradle.properties
echo "kotlin.build.cache.url=https://ge.jetbrains.com/cache/" >> ~/.gradle/gradle.properties

#enable kotlin-native
cat <<EOF > local.properties
kotlin.native.enabled=true
EOF

./gradlew classes testClasses assemble dist -Dscan.uploadInBackground=false
