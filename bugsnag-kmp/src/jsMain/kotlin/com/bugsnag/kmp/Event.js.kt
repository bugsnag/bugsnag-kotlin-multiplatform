package com.bugsnag.kmp

public actual typealias PlatformEvent = Any

public actual value class Event internal constructor(override val native: PlatformEvent) :
    PlatformWrapper<PlatformEvent> {

    private inline val nativeObj get() = native.asDynamic()

    public actual var apiKey: String
        get() = nativeObj.apiKey
        set(value) {
            nativeObj.apiKey = value
        }

    public actual var context: String?
        get() = nativeObj.context
        set(value) {
            nativeObj.context = value
        }

    public actual var groupingHash: String?
        get() = nativeObj.groupingHash
        set(value) {
            nativeObj.groupingHash = value
        }

    public actual var severity: Severity
        get() = nativeObj.severity
        set(value) {
            nativeObj.severity = value
        }

    public actual var user: User
        get() {
            val androidUser = nativeObj.user
            return User(
                id = androidUser.id,
                email = androidUser.email,
                name = androidUser.name,
            )
        }
        set(value) {
            nativeObj.user = User(name = value.name, id = value.id, email = value.email)
        }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        nativeObj.addFeatureFlag(name, variant)
    }

    public actual fun clearFeatureFlag(name: String) {
        nativeObj.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        nativeObj.clearFeatureFlags()
    }

    public actual fun addMetadata(
        section: String,
        data: Map<String, Any>,
    ) {
        nativeObj.addMetadata(section, data)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        nativeObj.addMetadata(section, key, value)
    }

    public actual fun clearMetadata(section: String) {
        nativeObj.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        nativeObj.clearMetadata(section, key)
    }
}
