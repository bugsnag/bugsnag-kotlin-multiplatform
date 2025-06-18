<div align="center">
  <a href="https://www.bugsnag.com/platforms/">
    <picture>
      <source media="(prefers-color-scheme: dark)" srcset="https://assets.smartbear.com/m/3dab7e6cf880aa2b/original/BugSnag-Repository-Header-Dark.svg">
      <img alt="SmartBear BugSnag logo" src="https://assets.smartbear.com/m/3945e02cdc983893/original/BugSnag-Repository-Header-Light.svg">
    </picture>
  </a>
  <h1>Error monitoring &amp; exception reporter for Kotlin with Multiplatform support</h1>
</div>

Detect crashes in your applications using Kotlin Multiplatform: collecting diagnostic information and immediately notifying your development team, helping you to understand and resolve issues as fast as possible.

## Beta SDK

This SDK is still in beta but builds upon on our existing [bugsnag-android](https://github.com/bugsnag/bugsnag-android/), [bugsnag-cocoa](https://github.com/bugsnag/bugsnag-cocoa) and [bugsnag-js](https://github.com/bugsnag/bugsnag-js) SDKs. As such it can be considered stable and safe for use in production, but possibly missing features in the Kotlin common layer that are available in the platform-specific SDKs. We are working to add more features to the common layer, but if you need a specific feature, please [open an issue](https://github.com/bugsnag/bugsnag-kotlin-multiplatform/issues/new?template=feature_request.md).

## Installation

Add `bugsnag-kmp` to your `build.gradle.kts` file:
```kotlin
dependencies {
    implementation("com.bugsnag:bugsnag-kmp:+")
}
```

In your Xcode project add `bugsnag-cocoa` as a Swift Package Manager (recommended) or other project dependency. See our online docs for instructions: https://docs.bugsnag.com/platforms/ios/#installation.

## Basic configuration

The `Bugsnag` client should be initialized as early as possible in your application lifecycle. On Android this is typically in the `onCreate` method of your `Application` class, while on iOS this is typically in the `didFinishLaunchingWithOptions` method of your `AppDelegate` class, or in the `init` method of your `App` class. Since we have Kotlin Multiplatform support, you can share most of your configuration code:

```kotlin
// BugsnagStartup.kt
import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration

fun startBugsnag(configuration: Configuration) {
   // common configuration here
   configuration.addMetadata("App", "multiplatform", true)
   Bugsnag.start(configuration)
}
```

`Bugsnag.start` should still be called in a platform-specific location:

```kotlin
 // don't accidentally import 'com.bugsnag.android.Configuration' if you want common configuration
import com.bugsnag.kmp.Configuration

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startBugsnag(Configuration(this, "your-api-key"))
    }
}
```

```swift
@main
struct iOSApp: App {
   init() {
      BugsnagStartupKt.startBugsnag(configuration: Bugsnag_kmpConfiguration(apiKey: "your-api-key"))
   }
}
```

## Enabled error types

The `Bugsnag` client can be configured to ignore certain types of errors. By default, all error types are enabled. You can enable or disable error types by calling the `setEnabledErrorTypes` method on the `Configuration` object.
In the `bugsnag-kmp` each platform-specific error type can be configured independently as part of the common source set:

```kotlin
import com.bugsnag.kmp.Configuration
// bugsnag-kmp includes a convenience extension function to set enabled error types in a lambda
import com.bugsnag.kmp.setEnabledErrorTypes

configuration.setEnabledErrorTypes {
   iosOoms = false
}
```

## Showing full stacktraces

To see fully symbolicated Kotlin and native stacktraces in your dashboard, we recommend using the [bugsnag-cli](https://docs.bugsnag.com/platforms/android/cli/) tool. This will find and upload the symbol files for each platform in your Kotlin Multiplatform project:

```bash
# upload the symbol files for the current build
bugsnag-cli upload android-aab app --api-key=your-api-key
bugsnag-cli upload xcode-archive iosApp --api-key=your-api-key 
```

If your API-key is defined in the `AndroidManifest.xml` and `Info.plist` files, you can omit the `--api-key` argument. The CLI will automatically find the API key in your project files. These steps are typically added to your CI/CD pipeline, so that the symbol files are uploaded automatically after each build.
## Support

* [Search open and closed issues](https://github.com/bugsnag/bugsnag-kmp/issues?utf8=✓&q=is%3Aissue) for similar problems
* [Report a bug or request a feature](https://github.com/bugsnag/bugsnag-kmp/issues/new)

## Contributing

All contributors are welcome!

## License

The BugSnag Kotlin SDK is free software released under the MIT License. See the [LICENSE](https://github.com/bugsnag/bugsnag-kmp/blob/main/LICENSE) for details.
