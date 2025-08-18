@file:JsModule("@bugsnag/core")

package com.bugsnag.js

@JsName("Event")
public external class Event {
    public constructor(errorClass: String, errorMessage: String)

    public var apiKey: String?
    public var context: String?
    public var groupingHash: String?
    public var severity: String
    public var device: Device
    public var app: App

    public fun getUser(): User
    public fun setUser(id: String?, email: String?, name: String?)
    public fun addFeatureFlag(name: String, variant: String?)
    public fun clearFeatureFlag(name: String)
    public fun clearFeatureFlags()
    public fun addMetadata(section: String, data: dynamic)
    public fun addMetadata(section: String, key: String, value: Any?)
    public fun clearMetadata(section: String)
    public fun clearMetadata(section: String, key: String)
}
