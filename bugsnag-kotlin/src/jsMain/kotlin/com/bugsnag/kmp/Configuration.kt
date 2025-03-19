package com.bugsnag.kmp

public actual typealias PlatformConfiguration = Any

public actual class Configuration(
    public actual override val native: PlatformConfiguration,
) : PlatformWrapper<PlatformConfiguration> {
    public constructor(apiKey: String) : this(Any()) {
        this.apiKey = apiKey
    }

    /**
     * Convenient access to our wrapped "config" object as `dynamic`
     */
    private inline val obj get() = native.asDynamic()

    public actual var apiKey: String
        get() = obj.apiKey as String
        set(value) {
            obj.apiKey = value
        }

    public actual var appVersion: String?
        get() = obj.appVersion as String?
        set(value) {
            obj.appVersion = value
        }

    public actual var autoTrackSessions: Boolean
        get() = obj.autoTrackSessions as Boolean
        set(value) {
            obj.autoTrackSessions = value
        }

    /**
     * Not supported on JS and will always appear to have a value of `0`
     */
    public actual var launchDurationMillis: Long
        get() = 0
        set(_) {}

    private val metadata: dynamic
        get() {
            var m = obj.metadata
            if (m == null) {
                m = Any().asDynamic()
                obj.metadata = m
            }
            return m
        }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        val map = mapOf(key to value)
        metadata[section]?.putAll(map.toSafeMetadata())
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        metadata[section]?.putAll(data.toSafeMetadata())
    }
}
