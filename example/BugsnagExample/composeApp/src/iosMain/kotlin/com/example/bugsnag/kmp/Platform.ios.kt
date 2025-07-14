package com.example.bugsnag.kmp

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual fun getPlatformBugsnagKey(): String {
    return "YOUR_IOS_BUGSNAG_API_KEY"
}
