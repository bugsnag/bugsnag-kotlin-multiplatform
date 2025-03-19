package com.bugsnag.kmp

@JsModule("@bugsnag/browser")
internal external object JsBugsnag {
    fun start(apiOrConfig: dynamic)
    fun addMetadata(section: String, key: String, value: Any?)
    fun addMetadata(section: String, data: dynamic)
}
