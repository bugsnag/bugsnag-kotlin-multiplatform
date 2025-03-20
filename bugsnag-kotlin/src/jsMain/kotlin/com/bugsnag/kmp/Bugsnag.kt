package com.bugsnag.kmp

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        JsBugsnag.start(configuration.native)
    }

    public actual fun startSession() {
        JsBugsnag.startSession()
    }

    public actual fun pauseSession() {
        JsBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = JsBugsnag.resumeSession()

    public actual fun clearFeatureFlag(name: String) {
        JsBugsnag.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        JsBugsnag.clearFeatureFlags()
    }
}
