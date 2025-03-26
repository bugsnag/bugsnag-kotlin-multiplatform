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

    public actual fun startSession() {
        PlatformBugsnag.startSession()
    }

    public actual fun pauseSession() {
        PlatformBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = PlatformBugsnag.resumeSession()

    public actual fun clearMetadata(section: String) {
        PlatformBugsnag.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        PlatformBugsnag.clearMetadata(section, key)
    }

    public actual fun clearFeatureFlag(name: String) {
        PlatformBugsnag.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        PlatformBugsnag.clearFeatureFlags()
    }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        PlatformBugsnag.addFeatureFlag(name, variant)
    }
}
