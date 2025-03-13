package com.bugsnag.kmp

@JsModule("@bugsnag/browser")
internal external object JsBugsnag {
    fun start(apiOrConfig: dynamic)
}
