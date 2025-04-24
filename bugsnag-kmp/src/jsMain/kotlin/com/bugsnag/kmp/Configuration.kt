package com.bugsnag.kmp

public actual typealias PlatformConfiguration = Any

private external fun delete(x: dynamic): Boolean

@Suppress("NOTHING_TO_INLINE")
private inline fun delete(thing: dynamic, section: String) = delete(thing[section])

public actual class Configuration(
    public actual override val native: PlatformConfiguration,
) : PlatformWrapper<PlatformConfiguration> {
    public constructor(apiKey: String) : this(Any()) {
        this.apiKey = apiKey
    }

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

    public actual var context: String?
        get() = obj.context as? String
        set(value) {
            obj.context = value
        }

    public actual var enabledReleaseStages: Set<String>?
        get() {
            return obj.enabledReleaseStages.unsafeCast<Array<String>?>()?.toSet()
        }
        set(value) {
            obj.enabledReleaseStages = value?.toTypedArray()
        }

    /**
     * Not supported on JS and will always appear to have a value of `0`
     */
    public actual var launchDurationMillis: Long
        get() = 0
        set(_) {}

    public actual var maxBreadcrumbs: Int
        get() = obj.maxBreadcrumbs.unsafeCast<Int>()
        set(value) {
            obj.maxBreadcrumbs = value
        }

    public actual var releaseStage: String?
        @Suppress("UnsafeCastFromDynamic")
        get() = obj.releaseStage
        set(value) {
            obj.releaseStage = value
        }

    public actual var user: User?
        get() {
            val jsUser = obj.user.unsafeCast<JsUser?>() ?: return null
            return User(jsUser.id, jsUser.email, jsUser.name)
        }
        set(value) {
            if (value != null) {
                obj.user = JsUser(value.id, value.email, value.name)
            }
        }

    public actual fun addRedactedKeys(redactedKeys: Collection<String>) {
        val existingRedactedKeys = this.redactedKeys.orEmpty()
        val newKeys = HashSet<String>(existingRedactedKeys.size + redactedKeys.size)
        newKeys.addAll(existingRedactedKeys)
        newKeys.addAll(redactedKeys)
        this.redactedKeys = newKeys.toTypedArray()
    }

    public actual fun addRedactedKeys(vararg redactedKeys: String) {
        val existingRedactedKeys = this.redactedKeys.orEmpty()
        val newKeys = HashSet<String>(existingRedactedKeys.size + redactedKeys.size)
        newKeys.addAll(existingRedactedKeys)
        newKeys.addAll(redactedKeys)
        this.redactedKeys = newKeys.toTypedArray()
    }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        val featureFlagArray = featureFlags
        if (featureFlagArray == null) {
            featureFlags = arrayOf(JsFeatureFlag(name, variant))
        } else {
            featureFlagArray.removeFirst { it.name == name }
            featureFlagArray.add(JsFeatureFlag(name, variant))
        }
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        val sectionObject = getMetadataSection(section)
        data.forEach { (key, value) ->
            sectionObject[key] = value.toSafeMetadata()
        }
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        val sectionObject = getMetadataSection(section)
        sectionObject[key] = value.toSafeMetadata()
    }

    public actual fun clearFeatureFlag(name: String) {
        featureFlags?.removeFirst { it.name == name }
    }

    public actual fun clearFeatureFlags() {
        featureFlags = null
    }

    public actual fun clearMetadata(section: String) {
        delete(metadata, section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        val metadataSection = metadata[section] ?: return
        delete(metadataSection, key)
    }

    public actual fun setEnabledErrorTypes(types: EnabledErrorTypes) {
        obj.enabledErrorTypes = JsErrorTypes(
            unhandledExceptions = types.jsUnhandledExceptions,
            unhandledRejections = types.jsUnhandledRejections,
        )
    }

    public actual fun setEndpoints(notify: String, sessions: String) {
        obj.endpoints = JsEndpointConfigurations(notify, sessions)
    }

    /**
     * Convenient access to our wrapped "config" object as `dynamic`
     */
    private inline val obj get() = native.asDynamic()

    private fun getMetadataSection(section: String): dynamic {
        var sectionObject = metadata[section]
        if (sectionObject == null) {
            sectionObject = Any().asDynamic()
            metadata[section] = sectionObject
        }
        return sectionObject
    }

    private val metadata: dynamic
        get() {
            var m = obj.metadata
            if (m == null) {
                m = Any().asDynamic()
                obj.metadata = m
            }
            return m
        }

    @Suppress("UnsafeCastFromDynamic")
    private var featureFlags: Array<JsFeatureFlag>?
        get() = obj.featureFlags
        set(value) {
            obj.featureFlags = value
        }

    private var redactedKeys: Array<String>?
        get() = obj.redactedKeys.unsafeCast<Array<String>?>()
        set(value) {
            obj.redactedKeys = value
        }
}
