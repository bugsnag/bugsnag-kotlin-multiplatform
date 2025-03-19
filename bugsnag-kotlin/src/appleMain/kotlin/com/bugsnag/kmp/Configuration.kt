@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import cocoapods.Bugsnag.BugsnagConfiguration
import kotlinx.cinterop.ExperimentalForeignApi

public actual typealias PlatformConfiguration = BugsnagConfiguration

public actual class Configuration(
    public actual override val native: PlatformConfiguration = PlatformConfiguration.loadConfig(),
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
        get() = native.launchDurationMillis.toLong()
        set(value) {
            native.launchDurationMillis = value.toULong()
        }

    public actual var autoTrackSessions: Boolean
        get() = native.autoTrackSessions
        set(value) {
            native.autoTrackSessions = value
        }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        native.addMetadata(value, key, section)
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        native.addMetadata(data as Map<Any?, *>, section)
    }
}
