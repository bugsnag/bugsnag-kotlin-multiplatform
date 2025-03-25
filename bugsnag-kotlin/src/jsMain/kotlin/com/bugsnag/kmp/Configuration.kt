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

    private var featureFlags: Array<FeatureFlag>?
        get() = obj.featureFlags
        set(value) {
            obj.featureFlags = value
        }

    public actual fun clearFeatureFlag(name: String) {
        featureFlags = featureFlags?.filter { it.name != name }?.toTypedArray()
    }

    public actual fun clearFeatureFlags() {
        featureFlags = null
    }

    internal fun createFeatureFlag(name: String, variant: String? = null): FeatureFlag {
        val flag = Any().unsafeCast<FeatureFlag>()
        flag.name = name

        if (variant != null) {
            flag.variant = variant
        }

        return flag
    }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        val flagArray = featureFlags
        val index = flagArray?.indexOfFirst { it.name == name }

        featureFlags = when {
            index == -1 -> flagArray + createFeatureFlag(name, variant)
            index?.let { flagArray.get(it).variant } != variant -> flagArray?.copyOf().also {
                if (index != null) {
                    it?.set(index, createFeatureFlag(name, variant))
                }
            }
            else -> return
        }
    }
}
