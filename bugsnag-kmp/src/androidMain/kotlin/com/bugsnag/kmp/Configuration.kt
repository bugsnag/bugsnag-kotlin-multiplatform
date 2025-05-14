package com.bugsnag.kmp

import android.content.Context
import com.bugsnag.android.EndpointConfiguration
import com.bugsnag.android.ErrorTypes
import java.util.regex.Pattern

public actual typealias PlatformConfiguration = com.bugsnag.android.Configuration

public actual class Configuration(
    public val androidContext: Context,
    public actual override val native: PlatformConfiguration =
        PlatformConfiguration.load(androidContext),
) : PlatformWrapper<PlatformConfiguration> {
    public constructor(androidContext: Context, apiKey: String) :
        this(androidContext, PlatformConfiguration(apiKey))

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

    public actual var autoTrackSessions: Boolean
        get() = native.autoTrackSessions
        set(value) {
            native.autoTrackSessions = value
        }

    public actual var context: String?
        get() = native.context
        set(value) {
            native.context = value
        }

    public actual var enabledReleaseStages: Set<String>?
        get() = native.enabledReleaseStages
        set(value) {
            native.enabledReleaseStages = value
        }

    public actual var launchDurationMillis: Long
        get() = native.launchDurationMillis
        set(value) {
            native.launchDurationMillis = value
        }

    public actual var maxBreadcrumbs: Int
        get() = native.maxBreadcrumbs
        set(value) {
            native.maxBreadcrumbs = value
        }

    public actual var releaseStage: String?
        get() = native.releaseStage
        set(value) {
            native.releaseStage = value
        }

    public actual var user: User?
        get() {
            val androidUser = native.getUser()
            return User(id = androidUser.id, name = androidUser.name, email = androidUser.email)
        }
        set(value) {
            if (value != null) {
                native.setUser(id = value.id, email = value.email, name = value.name)
            }
        }

    public var versionCode: Int?
        get() = native.versionCode
        set(value) {
            native.versionCode = value
        }

    public actual fun addRedactedKeys(redactedKeys: Collection<String>) {
        redactedKeys.mapTo(native.redactedKeys) { Pattern.compile(it, Pattern.LITERAL) }
    }

    public actual fun addRedactedKeys(vararg redactedKeys: String) {
        for (key in redactedKeys) {
            native.redactedKeys.add(Pattern.compile(key, Pattern.LITERAL))
        }
    }

    public actual fun addFeatureFlag(
        name: String,
        variant: String?,
    ) {
        native.addFeatureFlag(name, variant)
    }

    public actual fun addMetadata(
        section: String,
        data: Map<String, Any>,
    ) {
        native.addMetadata(section, data)
    }

    public actual fun addMetadata(
        section: String,
        key: String,
        value: Any?,
    ) {
        native.addMetadata(section, key, value)
    }

    public actual fun clearFeatureFlag(name: String) {
        native.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        native.clearFeatureFlags()
    }

    public actual fun clearMetadata(section: String) {
        native.clearMetadata(section)
    }

    public actual fun clearMetadata(
        section: String,
        key: String,
    ) {
        native.clearMetadata(section, key)
    }

    public actual fun setEnabledErrorTypes(types: EnabledErrorTypes) {
        native.enabledErrorTypes =
            ErrorTypes(
                anrs = types.androidAnrs,
                ndkCrashes = types.androidNdkCrashes,
                unhandledExceptions = types.androidUnhandledExceptions,
                unhandledRejections = types.androidUnhandledRejections,
            )
    }

    public actual fun setEndpoints(
        notify: String,
        sessions: String,
    ) {
        native.endpoints = EndpointConfiguration(notify, sessions)
    }
}
