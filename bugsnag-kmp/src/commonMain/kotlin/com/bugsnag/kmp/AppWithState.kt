package com.bugsnag.kmp

public expect class PlatformAppWithState

public expect value class AppWithState internal constructor(
    override val native: PlatformAppWithState,
) : PlatformWrapper<PlatformAppWithState> {

    public var binaryArch: String?
    public var id: String?
    public var releaseStage: String?
    public var version: String?
    public var codeBundleId: String?
    public var type: String?

    public var duration: Number?
    public var durationInForeground: Number?
    public var inForeground: Boolean?
    public var isLaunching: Boolean?
}
