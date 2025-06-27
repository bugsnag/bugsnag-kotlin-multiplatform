package com.bugsnag.kmp

import kotlin.js.Date

public actual external class PlatformDeviceWithState : JsDevice {
    override var id: String?
    override var hostname: String?
    override var locale: String?
    override var manufacturer: String?
    override var model: String?
    override var modelNumber: String?
    override var orientation: String?
    override var osName: String?
    override var osVersion: String?
    override var time: Date?
    override var userAgent: String?
}

public actual value class DeviceWithState internal constructor(
    override val native: PlatformDeviceWithState,
) : PlatformWrapper<PlatformDeviceWithState> {

    public actual var jailbroken: Boolean
        get() = false
        set(_) {}

    public actual var id: String?
        get() = native.id
        set(value) {
            native.id = value
        }

    public actual var locale: String?
        get() = native.locale
        set(value) {
            native.locale = value
        }

    public actual var manufacturer: String?
        get() = native.manufacturer
        set(value) {
            native.manufacturer = value
        }

    public actual var model: String?
        get() = native.model
        set(value) {
            native.model = value
        }

    public actual var osName: String?
        get() = native.osName
        set(value) {
            native.osName = value
        }

    public actual var osVersion: String?
        get() = native.osVersion
        set(value) {
            native.osVersion = value
        }

    public actual var totalMemory: Long?
        get() = null
        set(_) {}
}
