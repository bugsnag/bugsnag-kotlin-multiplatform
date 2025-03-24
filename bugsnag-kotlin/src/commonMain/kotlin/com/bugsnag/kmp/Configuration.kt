package com.bugsnag.kmp

public expect class PlatformConfiguration

public expect class Configuration : PlatformWrapper<PlatformConfiguration> {
    public override val native: PlatformConfiguration

    public var apiKey: String
    public var appVersion: String?
    public var launchDurationMillis: Long
    public var autoTrackSessions: Boolean
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
}
