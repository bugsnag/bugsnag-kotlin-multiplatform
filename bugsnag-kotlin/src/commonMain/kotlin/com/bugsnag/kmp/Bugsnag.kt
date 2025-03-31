package com.bugsnag.kmp

public expect object Bugsnag {
    public fun start(configuration: Configuration)
    public fun addMetadata(section: String, key: String, value: Any?)
    public fun addMetadata(section: String, data: Map<String, Any>)
    public fun startSession()
    public fun pauseSession()
    public fun resumeSession(): Boolean
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
    public fun clearFeatureFlag(name: String)
    public fun clearFeatureFlags()
    public fun addFeatureFlag(name: String, variant: String? = null)
    public var context: String?
    public var user: User
}
