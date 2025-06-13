package org.example.project

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

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
