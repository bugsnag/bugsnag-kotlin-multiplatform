package com.example.bugsnag.kmp.sharedui

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
