package com.bugsnag.js

@JsModule("@bugsnag/browser")
public external object Bugsnag {
    public fun getContext(): String?
    public fun setContext(value: String?)
    public fun getUser(): User
    public fun setUser(id: String?, email: String?, name: String?)
    public fun start(apiOrConfig: dynamic)
    public fun notify(error: Throwable)
    public fun notify(error: Throwable, onErrorCallback: (Event) -> Boolean)
    public fun leaveBreadcrumb(message: String, metadata: dynamic, type: String)
    public fun addFeatureFlag(name: String, variant: String?)
    public fun addMetadata(section: String, data: dynamic)
    public fun addMetadata(section: String, key: String, value: Any?)
    public fun clearFeatureFlag(name: String)
    public fun clearFeatureFlags()
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
    public fun isStarted(): Boolean
    public fun pauseSession()
    public fun resumeSession(): Boolean
    public fun startSession()
}

public external interface EndpointConfiguration {
    public var notify: String
    public var sessions: String
}

public fun EndpointConfiguration(notify: String, sessions: String): EndpointConfiguration {
    val endpoints = Any().unsafeCast<EndpointConfiguration>()
    endpoints.notify = notify
    endpoints.sessions = sessions
    return endpoints
}

public external interface FeatureFlag {
    public var name: String
    public var variant: String?
}

public fun FeatureFlag(name: String, variant: String?): FeatureFlag {
    val flag = Any().unsafeCast<FeatureFlag>()
    flag.name = name
    variant?.let { flag.variant = it }
    return flag
}

public external interface User {
    public var id: String?
    public var name: String?
    public var email: String?
}

public fun User(id: String?, email: String?, name: String?): User {
    val user = Any().unsafeCast<User>()
    id?.let { user.id = it }
    email?.let { user.email = it }
    name?.let { user.name = it }
    return user
}

public external interface EnabledErrorTypes {
    public var unhandledExceptions: Boolean?
    public var unhandledRejections: Boolean?
}

public fun EnabledErrorTypes(
    unhandledExceptions: Boolean = true,
    unhandledRejections: Boolean = true,
): EnabledErrorTypes {
    val errorTypes = Any().unsafeCast<EnabledErrorTypes>()
    errorTypes.unhandledExceptions = unhandledExceptions
    errorTypes.unhandledRejections = unhandledRejections
    return errorTypes
}
