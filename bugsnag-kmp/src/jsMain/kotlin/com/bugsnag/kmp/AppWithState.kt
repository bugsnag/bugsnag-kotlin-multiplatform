package com.bugsnag.kmp

public actual typealias PlatformAppWithState = JsAppWithState

public actual value class AppWithState
internal constructor(override val native: PlatformAppWithState) :
    PlatformWrapper<PlatformAppWithState> {

    public actual var binaryArch: String?
        get() = native.binaryArch
        set(value) {
            native.binaryArch = value
        }

    public actual var id: String?
        get() = native.id
        set(value) {
            native.id = value
        }

    public actual var releaseStage: String?
        get() = native.releaseStage
        set(value) {
            native.releaseStage = value
        }

    public actual var version: String?
        get() = native.version
        set(value) {
            native.version = value
        }

    public actual var codeBundleId: String?
        get() = native.codeBundleId
        set(value) {
            native.codeBundleId = value
        }

    public actual var type: String?
        get() = native.type
        set(value) {
            native.type = value
        }

    public actual var duration: Number?
        get() = native.duration
        set(value) {
            native.duration = value
        }
    public actual var durationInForeground: Number?
        get() = native.durationInForeground
        set(value) {
            native.durationInForeground = value
        }
    public actual var inForeground: Boolean?
        get() = native.inForeground
        set(value) {
            native.inForeground = value
        }
    public actual var isLaunching: Boolean?
        get() = native.isLaunching
        set(value) {
            native.isLaunching = value
        }
}
