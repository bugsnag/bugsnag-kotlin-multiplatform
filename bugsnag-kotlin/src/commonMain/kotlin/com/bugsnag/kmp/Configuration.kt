package com.bugsnag.kmp

public expect class PlatformConfiguration

public expect class Configuration : PlatformWrapper<PlatformConfiguration> {
    public override val native: PlatformConfiguration

    public var apiKey: String
    public var appVersion: String?
    public var launchDurationMillis: Long
    public var autoTrackSessions: Boolean
    public fun addMetadata(section: String, key: String, value: Any?)
    public fun addMetadata(section: String, data: Map<String, Any>)
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
    public fun clearFeatureFlag(name: String)
    public fun clearFeatureFlags() 
}
