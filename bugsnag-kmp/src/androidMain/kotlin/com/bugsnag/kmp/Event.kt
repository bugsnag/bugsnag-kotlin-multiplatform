package com.bugsnag.kmp

public actual typealias PlatformEvent = com.bugsnag.android.Event

@JvmInline
public actual value class Event internal constructor(
    override val native: PlatformEvent,
) : PlatformWrapper<PlatformEvent> {
    public actual var apiKey: String
        get() = native.apiKey
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
            val androidUser = native.getUser()
            return User(
                id = androidUser.id,
                email = androidUser.email,
                name = androidUser.name,
            )
        }
        set(value) {
            native.setUser(name = value.name, id = value.id, email = value.email)
        }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        native.addFeatureFlag(name, variant)
    }

    public actual fun clearFeatureFlag(name: String) {
        native.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        native.clearFeatureFlags()
    }

    public actual fun addMetadata(
        section: String,
        data: Map<String, Any>,
    ) {
        native.addMetadata(section, data)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        native.addMetadata(section, key, value)
    }

    public actual fun clearMetadata(section: String) {
        native.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        native.clearMetadata(section, key)
    }
}
