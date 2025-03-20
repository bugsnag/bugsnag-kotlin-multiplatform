@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import cocoapods.Bugsnag.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        PlatformBugsnag.startWithConfiguration(configuration.native)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        PlatformBugsnag.addMetadata(value, key, section)
    }

    @Suppress("UNCHECKED", "UNCHECKED_CAST")
    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        PlatformBugsnag.addMetadata(data as Map<Any?, *>, section)
    }

    public actual fun startSession() {
        PlatformBugsnag.startSession()
    }

    public actual fun pauseSession() {
        PlatformBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = PlatformBugsnag.resumeSession()
}
