@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import com.bugsnag.cocoa.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        PlatformBugsnag.startWithConfiguration(configuration.native)
    }

    public actual fun startSession() {
        PlatformBugsnag.startSession()
    }

    public actual fun pauseSession() {
        PlatformBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = PlatformBugsnag.resumeSession()
}
