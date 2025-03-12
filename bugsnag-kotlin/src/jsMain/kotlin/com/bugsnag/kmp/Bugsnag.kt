package com.bugsnag.kmp

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        JsBugsnag.start(configuration.native)
    }
}
