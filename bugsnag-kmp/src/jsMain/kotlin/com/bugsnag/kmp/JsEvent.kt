@file:JsModule("@bugsnag/core")

package com.bugsnag.kmp

@JsName("Event")
public external class JsEvent {
    public constructor(errorClass: String, errorMessage: String)

    public var apiKey: String?
    public var context: String?
    public var groupingHash: String?
    public var severity: String
    public var app: JsApp
    internal fun getUser(): JsUser
    public fun setUser(id: String?, email: String?, name: String?)
    public fun addFeatureFlag(name: String, variant: String?)
    public fun clearFeatureFlag(name: String)
    public fun clearFeatureFlags()
    public fun addMetadata(section: String, data: dynamic)
    public fun addMetadata(section: String, key: String, value: Any?)
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
}
