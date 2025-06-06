# Bugsnag Kotlin Multiplatform - ExampleApp

This repository demonstrates how to integrate [Bugsnag KMP](https://github.com/bugsnag/bugsnag-kotlin-multiplatform) into a Kotlin Multiplatform (KMP) application across **Android**, **iOS**, and **JavaScript Web** platforms.

## Project Structure
examples/ExampleApp/
├── androidApp/ # Android app with Jetpack Compose UI
├── iosExampleApp/ # iOS app with SwiftUI
├── webApp/ # Web app using Kotlin/JS and HTML
├── shared/ # Shared KMP module with Bugsnag integration
└── build.gradle.kts # Root build configuration
---

## 🤖 Android App

### Build & Run

```bash
cd examples/ExampleApp
./gradlew clean build
Open in Android Studio
Set your Bugsnag API Key at `androidApp/src/main/java/com/example/exampleapps/android/MainActivity.kt`
``` 

### Features
Notify Bugsnag with a manual error

Trigger a fatal crash

Set custom metadata

Leave a breadcrumb

Simulate an ANR

## iOS App

### Build & Run

```bash
open iosExampleApp/iosExampleApp.xcworkspace
Select an ios sim or device
Set your Bugsnag API Key at `iosApp/iOSApp.swift`
Run the app
```

### Features

Manual Bugsnag notification

Use shared KMP code

Trigger crash via button

## Web App

### Build & Run

```bash
cd examples/ExampleApp
./gradlew :webApp:jsBrowserDevelopmentRun
open http://localhost:8080
```

### Features
Display UI with HTML/JS

Buttons call shared Bugsnag methods:

Manual notify

Trigger crash

Set metadata

Leave breadcrumb

## Shared KMP Module
The shared module contains the Bugsnag integration code used across all platforms. It includes:
- Bugsnag initialization
- Manual error reporting
- Custom metadata handling
- Breadcrumbs management

## Troubleshooting

Can't find shared module in iOS project?
``` bash
run ./gradlew syncFramework
link the generated framework in Xcode
```