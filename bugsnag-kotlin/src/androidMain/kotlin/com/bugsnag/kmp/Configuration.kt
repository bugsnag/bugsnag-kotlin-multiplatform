package com.bugsnag.kmp

import android.content.Context
import com.bugsnag.android.ErrorTypes

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

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        native.addMetadata(section, key, value)
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        native.addMetadata(section, data)
    }

    public actual fun clearMetadata(section: String) {
        native.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        native.clearMetadata(section, key)
    }

    public actual fun clearFeatureFlag(name: String) {
        native.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        native.clearFeatureFlags()
    }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        native.addFeatureFlag(name, variant)
    }

    public actual var context: String?
        get() = native.context
        set(value) {
            native.context = value
        }
    public actual var enabledErrorTypes: EnabledErrorTypes
        get() {
            val errorTypes = native.enabledErrorTypes
            return EnabledErrorTypes(
                androidAnrs = errorTypes.anrs,
                androidNdkCrashes = errorTypes.ndkCrashes,
                androidUnhandledExceptions = errorTypes.unhandledExceptions,
                androidUnhandledRejections = errorTypes.unhandledRejections,
            )
        }
        set(value) {
            native.enabledErrorTypes = ErrorTypes(
                anrs = value.androidAnrs,
                ndkCrashes = value.androidNdkCrashes,
                unhandledExceptions = value.androidUnhandledExceptions,
                unhandledRejections = value.androidUnhandledRejections,
            )
        }
}
