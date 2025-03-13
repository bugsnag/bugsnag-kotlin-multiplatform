@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import cocoapods.Bugsnag.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        PlatformBugsnag.startWithConfiguration(configuration.native)
    }
}
