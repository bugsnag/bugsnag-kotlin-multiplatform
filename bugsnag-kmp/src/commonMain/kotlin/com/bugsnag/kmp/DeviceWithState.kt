package com.bugsnag.kmp

public expect class PlatformDeviceWithState

public expect value class DeviceWithState internal constructor(
    override val native: PlatformDeviceWithState,
) : PlatformWrapper<PlatformDeviceWithState> {
    public var jailbroken: Boolean
    public var id: String?
    public var locale: String?
    public var manufacturer: String?
    public var model: String?
    public var osName: String?
    public var osVersion: String?
    public var totalMemory: Long?
}
