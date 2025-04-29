## Getting Started

### Dependencies

Add `bugsnag-kmp` to your `build.gradle.kts` file:
```kotlin
dependencies {
    implementation("com.bugsnag.android:bugsnag-kmp:+")
}
```

In your XCode project add [bugsnag-cocoa](https://docs.bugsnag.com/platforms/ios/#using-swift-package-manager) as a dependency.

### Initialization & Configuration

Bugsnag should be initialized as early as possible in your application lifecycle. On Android this is typically in the `onCreate` method of your `Application` class, while on iOS this is typically in the `didFinishLaunchingWithOptions` method of your `AppDelegate` class, or in the `init` method of your `App` class. Since we have Kotlin Multiplatform support, you can share most your configuration code:

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

#### Enabled Error Types

The Bugsnag SDK can be configured to ignore certain types of errors. By default, all error types are enabled. You can enable or disable error types by calling the `setEnabledErrorTypes` method on the `Configuration` object.
In the `bugsnag-kmp` each platform-specific error type can be configured independently as part of the common source set:

```kotlin
import com.bugsnag.kmp.Configuration
// bugsnag-kmp includes a convenience extension function to set enabled error types in a lambda
import com.bugsnag.kmp.setEnabledErrorTypes

configuration.setEnabledErrorTypes {
   iosOoms = false
}
```

### Showing full stacktraces (symbolication)

The easiest way to upload your symbol files for a Kotlin Multiplatform project is with the [bugsnag-cli](https://docs.bugsnag.com/platforms/android/cli/) tool. This will find and upload the symbol files for each platform in your project:

```bash
# upload the symbol files for the current build
bugsnag-cli upload android-aab app --api-key=your-api-key
bugsnag-cli upload xcode-archive iosApp --api-key=your-api-key 
```

If your API-key is defined in the `AndroidManifest.xml` and `Info.plist` files, you can omit the `--api-key` argument. The CLI will automatically find the API key in your project files. These steps are typically added to your CI/CD pipeline, so that the symbol files are uploaded automatically after each build.