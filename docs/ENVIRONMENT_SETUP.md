# Environment setup

This contains information on first-time setup required to develop bugsnag-kmp. Please raise a PR if any steps are missing as these dependencies commonly change.

## Pre-requisites

- Java 17 JDK, this can be installed with [sdkman](https://sdkman.io/)
- A working [git](https://git-scm.com/) installation
- A ruby installation (can be skipped until running E2E tests)

## Initial setup

1. Download the [latest stable version](https://developer.android.com/studio) of Android Studio.
3. Set the `$ANDROID_SDK_ROOT` environment variables to point to the SDK
4. Add the adb/android/emulator [platform tools](https://developer.android.com/studio/command-line/variables) to your `$PATH`
4. Clone the repository and its submodules: `git submodule update --init --recursive`
5. Open the project in Android Studio to trigger indexing and downloading of dependencies
