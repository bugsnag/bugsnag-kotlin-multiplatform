package com.bugsnag.js

import kotlin.js.Date

public external interface Device {
    public var id: String?
    public var hostname: String?
    public var locale: String?
    public var manufacturer: String?
    public var model: String?
    public var modelNumber: String?
    public var orientation: String?
    public var osName: String?
    public var osVersion: String?
    public var time: Date?
    public var userAgent: String?
}
