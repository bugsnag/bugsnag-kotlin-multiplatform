package com.bugsnag.kmp

public external interface JsApp {
    public var codeBundleId: String?
    public var duration: Number?
    public var durationInForeground: Number?
    public var inForeground: Boolean?
    public var releaseStage: String?
    public var type: String?
    public var version: String?
}
