package com.bugsnag.kmp

import android.content.Context

public actual typealias PlatformConfiguration = com.bugsnag.android.Configuration

public actual class Configuration(
    public val androidContext: Context,
    public actual override val native: PlatformConfiguration =
        PlatformConfiguration.load(androidContext),
) : PlatformWrapper<PlatformConfiguration> {
    public actual var apiKey: String
        get() = native.apiKey
        set(value) {
            native.apiKey = value
        }

    public actual var appVersion: String?
        get() = native.appVersion
        set(value) {
            native.appVersion = value
        }

    public actual var launchDurationMillis: Long
        get() = native.launchDurationMillis
        set(value) {
            native.launchDurationMillis = value
        }

    public actual var autoTrackSessions: Boolean
        get() = native.autoTrackSessions
        set(value) {
            native.autoTrackSessions = value
        }

    public var versionCode: Int?
        get() = native.versionCode
        set(value) {
            native.versionCode = value
        }

    public actual fun clearMetadata(section: String) {
        native.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        native.clearMetadata(section, key)
    }
}
