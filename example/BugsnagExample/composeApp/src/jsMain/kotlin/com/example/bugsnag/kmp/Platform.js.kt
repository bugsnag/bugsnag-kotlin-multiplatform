package com.example.bugsnag.kmp

import platform.UIKit.UIDevice


actual fun getPlatformName(): String {
    return "JS"
}

actual fun getPlatformBugsnagKey(): String {
    return "YOUR_JS_BUGSNAG_API_KEY"
}
