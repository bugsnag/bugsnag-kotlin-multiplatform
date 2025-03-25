package com.bugsnag.kmp

@JsModule("@bugsnag/browser")
internal external object JsBugsnag {
    fun start(apiOrConfig: dynamic)

    fun startSession()
    fun pauseSession()
    fun resumeSession(): Boolean
    fun clearFeatureFlag(name: String)
    fun clearFeatureFlags()
    fun addFeatureFlag(name: String, variant: String?)
}
