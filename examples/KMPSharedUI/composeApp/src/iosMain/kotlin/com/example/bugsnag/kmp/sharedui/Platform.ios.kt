package com.example.bugsnag.kmp.sharedui

import kotlinx.cinterop.ExperimentalForeignApi

actual fun getPlatformName(): String {
    return "iOS"
}

actual fun getPlatformBugsnagKey(): String {
    return "YOUR_IOS_BUGSNAG_API_KEY"
}

@OptIn(ExperimentalForeignApi::class)
actual fun throwNative() {
    NativeCaller().throwNative()
}

@OptIn(ExperimentalForeignApi::class)
actual fun throwNotResponding() {
    NativeCaller().throwNativeAppHang()
}
