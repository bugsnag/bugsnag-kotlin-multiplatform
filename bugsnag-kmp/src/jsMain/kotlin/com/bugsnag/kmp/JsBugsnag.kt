package com.bugsnag.kmp

@JsModule("@bugsnag/browser")
internal external object JsBugsnag {
    var context: String?
    var user: JsUser

    fun start(apiOrConfig: dynamic)
    fun notify(error: Throwable)
    fun notify(error: Throwable, onErrorCallback: (JsEvent) -> Boolean)
    fun leaveBreadcrumb(message: String, metadata: dynamic, type: String)
    fun addFeatureFlag(name: String, variant: String?)
    fun addMetadata(section: String, data: dynamic)
    fun addMetadata(section: String, key: String, value: Any?)
    fun clearFeatureFlag(name: String)
    fun clearFeatureFlags()
    fun clearMetadata(section: String)
    fun clearMetadata(section: String, key: String)
    fun isStarted(): Boolean
    fun pauseSession()
    fun resumeSession(): Boolean
    fun startSession()
}

internal external interface JsEndpointConfigurations {
    var notify: String
    var sessions: String
}

internal fun JsEndpointConfigurations(notify: String, sessions: String): JsEndpointConfigurations {
    val endpoints = Any().unsafeCast<JsEndpointConfigurations>()
    endpoints.notify = notify
    endpoints.sessions = sessions
    return endpoints
}

internal external interface JsFeatureFlag {
    var name: String
    var variant: String?
}

internal fun JsFeatureFlag(name: String, variant: String?): JsFeatureFlag {
    val flag = Any().unsafeCast<JsFeatureFlag>()
    if (variant != null) {
        flag.name = name
        flag.variant = variant
    } else {
        flag.name = name
    }
    return flag
}

internal external interface JsUser {
    var id: String?
    var name: String?
    var email: String?
}

internal fun JsUser(id: String?, email: String?, name: String?): JsUser {
    val user = Any().unsafeCast<JsUser>()
    id?.let { user.id = it }
    email?.let { user.email = it }
    name?.let { user.name = it }
    return user
}

internal external interface JsErrorTypes {
    var unhandledExceptions: Boolean?
    var unhandledRejections: Boolean?
}

internal fun JsErrorTypes(
    unhandledExceptions: Boolean?,
    unhandledRejections: Boolean?,
): JsErrorTypes {
    val errorTypes = Any().unsafeCast<JsErrorTypes>()
    errorTypes.unhandledExceptions = unhandledExceptions
    errorTypes.unhandledRejections = unhandledRejections
    return errorTypes
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
