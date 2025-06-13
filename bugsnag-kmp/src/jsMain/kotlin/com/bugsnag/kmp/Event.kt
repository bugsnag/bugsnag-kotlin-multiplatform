package com.bugsnag.kmp

public actual typealias PlatformEvent = JsEvent

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
        get() = Severity.getSeverity(native.severity)
        set(value) {
            native.severity = Severity.getSeverityName(value)
        }

    public actual var user: User
        get() {
            val jsUser = native.getUser()
            return User(
                id = jsUser.id,
                email = jsUser.email,
                name = jsUser.name,
            )
        }
        set(value) {
            native.setUser(value.id, value.email, value.name)
        }

    public actual val app: AppWithState
        get() = native.app

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
        native.addMetadata(section, data.toSafeMetadata())
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        native.addMetadata(section, key, value.toSafeMetadata())
    }

    public actual fun clearMetadata(section: String) {
        native.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        native.clearMetadata(section, key)
    }
}
