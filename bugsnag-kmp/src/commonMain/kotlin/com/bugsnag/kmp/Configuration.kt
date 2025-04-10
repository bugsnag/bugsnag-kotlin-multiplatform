@file:JvmName("CommonConfigurationKt")

package com.bugsnag.kmp

import kotlin.jvm.JvmName

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
    public fun addFeatureFlag(name: String, variant: String? = null)
    public var context: String?
    public var user: User?
    public fun setEnabledErrorTypes(types: EnabledErrorTypes)
    public fun setEndpoints(
        notify: String = "https://notify.bugsnag.com",
        sessions: String = "https://sessions.bugsnag.com",
    )
}

public fun Configuration.setEnabledErrorTypes(configure: EnabledErrorTypes.() -> Unit) {
    val enabledErrorTypes = EnabledErrorTypes()
    enabledErrorTypes.configure()
    setEnabledErrorTypes(enabledErrorTypes)
}
