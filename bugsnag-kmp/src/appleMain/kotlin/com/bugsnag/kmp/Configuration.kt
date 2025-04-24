@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import com.bugsnag.cocoa.BugsnagConfiguration
import com.bugsnag.cocoa.BugsnagEndpointConfiguration
import com.bugsnag.cocoa.BugsnagErrorTypes
import kotlinx.cinterop.ExperimentalForeignApi

public actual typealias PlatformConfiguration = BugsnagConfiguration

public actual class Configuration(
    public actual override val native: PlatformConfiguration = PlatformConfiguration.loadConfig(),
) : PlatformWrapper<PlatformConfiguration> {
    public constructor(apiKey: String) : this(PlatformConfiguration(apiKey))

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
        get() = native.context()
        set(value) {
            native.setContext(value)
        }

    public actual var enabledReleaseStages: Set<String>?
        get() {
            @Suppress("UNCHECKED_CAST")
            return native.enabledReleaseStages as? Set<String>
        }
        set(value) {
            native.enabledReleaseStages = value as Set<*>
        }

    public actual var launchDurationMillis: Long
        get() = native.launchDurationMillis.toLong()
        set(value) {
            native.launchDurationMillis = value.toULong()
        }

    public actual var maxBreadcrumbs: Int
        get() = native.maxBreadcrumbs.toInt()
        set(value) {
            native.maxBreadcrumbs = value.toULong()
        }

    public actual var releaseStage: String?
        get() = native.releaseStage
        set(value) {
            native.releaseStage = value
        }

    public actual var user: User?
        get() {
            val appleUser = native.user()
            return User(
                id = appleUser.id,
                email = appleUser.email,
                name = appleUser.name,
            )
        }
        set(value) {
            if (value != null) {
                native.setUser(value.id, value.email, value.name)
            }
        }

    public actual fun addRedactedKeys(redactedKeys: Collection<String>) {
        native.redactedKeys = native.redactedKeys.orEmpty() + redactedKeys
    }

    public actual fun addRedactedKeys(vararg redactedKeys: String) {
        @Suppress("UNCHECKED_CAST")
        val existingKeys: MutableSet<String> = when (val keys = native.redactedKeys) {
            is HashSet<*> -> keys as MutableSet<String>
            null -> mutableSetOf()
            else -> keys.toMutableSet() as MutableSet<String>
        }

        existingKeys.addAll(redactedKeys)
    }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        native.addFeatureFlagWithName(name, variant)
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        @Suppress("UNCHECKED_CAST")
        native.addMetadata(data as Map<Any?, *>, section)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        native.addMetadata(value, key, section)
    }

    public actual fun clearFeatureFlag(name: String) {
        native.clearFeatureFlagWithName(name)
    }

    public actual fun clearFeatureFlags() {
        native.clearFeatureFlags()
    }

    public actual fun clearMetadata(section: String) {
        native.clearMetadataFromSection(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        native.clearMetadataFromSection(section, key)
    }

    public actual fun setEnabledErrorTypes(types: EnabledErrorTypes) {
        val errorTypes = BugsnagErrorTypes().apply {
            this.setAppHangs(types.iosAppHangs)
            this.setOoms(types.iosOoms)
            this.setThermalKills(types.iosThermalKills)
            this.setUnhandledExceptions(types.iosUnhandledExceptions)
            this.setSignals(types.iosSignals)
            this.setCppExceptions(types.iosCppExceptions)
            this.setMachExceptions(types.iosMachExceptions)
            this.setUnhandledRejections(types.iosUnhandledRejections)
        }
        native.enabledErrorTypes = errorTypes
    }

    public actual fun setEndpoints(notify: String, sessions: String) {
        native.endpoints = BugsnagEndpointConfiguration(notify, sessions)
    }
}
