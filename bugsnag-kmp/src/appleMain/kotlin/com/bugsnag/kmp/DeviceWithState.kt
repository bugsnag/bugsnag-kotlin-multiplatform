@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSNumber

public actual typealias PlatformDeviceWithState = com.bugsnag.cocoa.BugsnagDeviceWithState

public actual value class DeviceWithState internal constructor(
    override val native: PlatformDeviceWithState,
) : PlatformWrapper<PlatformDeviceWithState> {
    public actual var jailbroken: Boolean
        get() = native.jailbroken
        set(value) {
            native.jailbroken = value
        }

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
        get() = native.totalMemory?.longValue
        set(value) {
            native.totalMemory = value?.let { NSNumber(long = it.toLong()) }
        }
}
