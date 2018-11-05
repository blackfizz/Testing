#!/bin/bash

# get input params
branchName=$1
buildType=$2
storePass=$3
keyAlias=$4
keyPass=$5

# helper method
setProperty() {
	sed -i.bak -e "s/\($1 *= *\).*/\1$2/" ${propertiesFile}
}

propertiesFile='gradle.properties'
chmod +x ${propertiesFile}

# update key properties based on build type
if [ $buildType = 'debug' ]; then
	(setProperty "KEYSTORE" "debug.keystore")
	(setProperty "STORE_PASSWORD" "asdfgx")
	(setProperty "KEY_ALIAS" "key0")
	(setProperty "KEY_PASSWORD" "asdfgx")
elif [ $buildType = 'release' ]; then
	(setProperty "KEYSTORE" "release.keystore")
	(setProperty "STORE_PASSWORD" "$storePass")
	(setProperty "KEY_ALIAS" "$keyAlias")
	(setProperty "KEY_PASSWORD" "$keyPass")
fi

# clean project
chmod +x gradlew
./gradlew clean --stacktrace

# build
if [ $buildType = 'debug' ]; then
	./gradlew assembleDebug --stacktrace
elif [ $buildType = 'release' ]; then
	./gradlew assembleRelease --stacktrace
fi

# ready expected final APK
apkFileName="app-$buildType.apk"

# check if exists, if not exit with error
if [ ! -e "app/build/outputs/apk/$buildType/$apkFileName" ]; then
    echo "ERROR: File not exists: (app/build/outputs/apk/$buildType/$apkFileName)"
    exit 1
fi

rm -r artifacts/
mkdir artifacts
cp app/build/outputs/apk/$buildType/$apkFileName artifacts/