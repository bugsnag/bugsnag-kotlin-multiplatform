package com.bugsnag.kmp

import com.bugsnag.android.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        PlatformBugsnag.start(configuration.androidContext, configuration.native)
    }
}
