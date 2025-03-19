package com.bugsnag.kmp

public expect object Bugsnag {
    public fun start(configuration: Configuration)
    public fun addMetadata( section: String, key: String, value: Any?)
    public fun addMetadata(section: String, data: Map<String, Any>)
}
