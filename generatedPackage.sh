#!/bin/bash
# Create new package to app
apk_name="ArcadiaCaller.apk"
echo "Create and publish new apk version: $apk_name!"

echo "Build to app"
gradle :app:assembleDebug

echo "Copy to apk file"
cp ./app/build/outputs/apk/app-debug.apk ./app/build/outputs/apk/$apk_name

echo "Add apk to git"
cp ./app/build/outputs/apk/app-debug.apk ./$apk_name
git add --force ./$apk_name
git ci -m "[shell] Generate new version $apk_name"

echo "finish"
