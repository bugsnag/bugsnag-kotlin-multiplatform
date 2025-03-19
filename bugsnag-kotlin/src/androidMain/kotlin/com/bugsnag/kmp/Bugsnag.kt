package com.bugsnag.kmp

import com.bugsnag.android.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        PlatformBugsnag.start(configuration.androidContext, configuration.native)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        PlatformBugsnag.addMetadata(section, key, value)
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        PlatformBugsnag.addMetadata(section, data)
    }
}
