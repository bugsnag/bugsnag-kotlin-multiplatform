package org.example.project

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getPlatformName(): String {
    return "Android"
}

actual fun getPlatformBugsnagKey(): String {
    return "YOUR_ANDROID_BUGSNAG_API_KEY"
}

actual fun throwNative() {
    NativeCaller().throwNative()
}

actual fun throwNotResponding() {
    NativeCaller().throwNativeANR()
}
