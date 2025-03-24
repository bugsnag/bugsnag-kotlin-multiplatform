package com.bugsnag.kmp

public expect object Bugsnag {
    public fun start(configuration: Configuration)

    public fun startSession()
    public fun pauseSession()
    public fun resumeSession(): Boolean
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
}
