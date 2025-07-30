@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi

public actual typealias PlatformEvent = com.bugsnag.cocoa.BugsnagEvent

public actual value class Event internal constructor(
    override val native: PlatformEvent,
) : PlatformWrapper<PlatformEvent> {
    public actual var apiKey: String
        get() = native.apiKey ?: ""
        set(value) {
            native.apiKey = value
        }

    public actual var context: String?
        get() = native.context
        set(value) {
            native.context = value
        }

    public actual var groupingHash: String?
        get() = native.groupingHash
        set(value) {
            native.groupingHash = value
        }

    public actual var severity: Severity
        get() = native.severity.toPlatformType()
        set(value) {
            native.severity = value.toNativePlatformType()
        }

    public actual var user: User
        get() {
            val appleUser = native.user
            return User(
                id = appleUser.id,
                email = appleUser.email,
                name = appleUser.name,
            )
        }
        set(value) {
            native.setUser(userId = value.id, withEmail = value.email, andName = value.name)
        }

    public actual val device: DeviceWithState
        get() = DeviceWithState(native = native.device())

    public actual val app: AppWithState
        get() = AppWithState(native = native.app())

    public actual fun addFeatureFlag(name: String, variant: String?) {
        native.addFeatureFlagWithName(name, variant)
    }

    public actual fun clearFeatureFlag(name: String) {
        native.clearFeatureFlagWithName(name)
    }

    public actual fun clearFeatureFlags() {
        native.clearFeatureFlags()
    }

    public actual fun addMetadata(
        section: String,
        data: Map<String, Any>,
    ) {
        @Suppress("UNCHECKED_CAST")
        native.addMetadata(data as Map<Any?, *>, section)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        native.addMetadata(value, key, section)
    }

    public actual fun clearMetadata(section: String) {
        native.clearMetadataFromSection(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        native.clearMetadataFromSection(section, key)
    }
}
