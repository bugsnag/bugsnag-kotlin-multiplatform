package com.bugsnag.kmp

public expect class PlatformEvent

public expect value class Event internal constructor(
    override val native: PlatformEvent,
) : PlatformWrapper<PlatformEvent> {

    public var apiKey: String
    public var context: String?
    public var groupingHash: String?
    public var severity: Severity
    public var user: User
    public val app: AppWithState

    public fun addFeatureFlag(name: String, variant: String? = null)
    public fun clearFeatureFlag(name: String)
    public fun clearFeatureFlags()

    public fun addMetadata(section: String, data: Map<String, Any>)
    public fun addMetadata(section: String, key: String, value: Any?)
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
}
