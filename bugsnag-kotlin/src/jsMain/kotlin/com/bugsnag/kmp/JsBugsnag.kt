package com.bugsnag.kmp

@JsModule("@bugsnag/browser")
internal external object JsBugsnag {
    fun start(apiOrConfig: dynamic)

    fun startSession()
    fun pauseSession()
    fun resumeSession(): Boolean
    fun clearMetadata(section: String)
    fun clearMetadata(section: String, key: String)
}
