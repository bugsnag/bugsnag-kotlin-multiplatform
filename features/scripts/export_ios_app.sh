#!/usr/bin/env bash

set -o errexit

# "Release" or "Debug" must be specified
if [ "$1" != "Release" ] && [ "$1" != "Debug" ]; then
  echo "Usage: $0 [Release|Debug]"
  exit 1
fi

BUILD_CONFIGURATION=$1
pushd features/fixtures/mazerunner/iosApp

  echo "--- iosApp: xcodebuild archive"

  xcrun xcodebuild \
    -scheme iosApp \
    -workspace iosApp.xcworkspace \
    -destination generic/platform=iOS \
    -configuration ${BUILD_CONFIGURATION} \
    -archivePath archive/iosApp.xcarchive \
    -allowProvisioningUpdates \
    -quiet \
    archive \
    CLANG_ENABLE_MODULES=NO \
    GCC_PREPROCESSOR_DEFINITIONS='$(inherited) BSG_LOG_LEVEL=BSG_LOGLEVEL_DEBUG BSG_KSLOG_ENABLED=1'

  echo "--- iosApp: xcodebuild -exportArchive"

  xcrun xcodebuild \
    -exportArchive \
    -archivePath archive/iosApp.xcarchive \
    -destination generic/platform=iOS \
    -exportPath output/ \
    -quiet \
    -exportOptionsPlist exportOptions.plist

  pushd output
    mv Mazerunner.ipa Mazerunner_$BUILD_CONFIGURATION.ipa
  popd
popd