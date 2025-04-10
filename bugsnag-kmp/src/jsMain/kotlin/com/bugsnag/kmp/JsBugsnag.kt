package com.bugsnag.kmp

@JsModule("@bugsnag/browser")
internal external object JsBugsnag {
    fun start(apiOrConfig: dynamic)
    fun isStarted(): Boolean
    fun addMetadata(section: String, key: String, value: Any?)
    fun addMetadata(section: String, data: dynamic)
    fun startSession()
    fun pauseSession()
    fun resumeSession(): Boolean
    fun clearMetadata(section: String)
    fun clearMetadata(section: String, key: String)
    fun clearFeatureFlag(name: String)
    fun clearFeatureFlags()
    fun addFeatureFlag(name: String, variant: String?)
    var context: String?
    var user: JsUser
}

internal fun <V> Map<*, V>.convertToDynamic(): dynamic {
    val dynamicData = Any().asDynamic()
    forEach { (key, value) ->
        dynamicData[key] = value.toSafeMetadata()
    }
    return dynamicData
}

internal fun Any?.toSafeMetadata(): Any? = when (this) {
    null -> null
    is Double -> this
    is Long -> this
    is Int -> this
    is Boolean -> this
    is Short -> this
    is Float -> this
    is Map<*, *> -> convertToDynamic()
    is List<*> -> map { it.toSafeMetadata() }.toTypedArray()
    is Array<*> -> Array(size) { index -> this[index].toSafeMetadata() }
    is String -> this
    is Byte -> this
    else -> this.toString()
}
