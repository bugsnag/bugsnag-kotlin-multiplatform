package com.bugsnag.kmp

public data class EnabledErrorTypes(
    val androidAnrs: Boolean = true,
    val androidNdkCrashes: Boolean = true,
    val androidUnhandledExceptions: Boolean = true,
    val androidUnhandledRejections: Boolean = true,

    val iosAppHangs: Boolean = true,
    val iosOoms: Boolean = true,
    val iosThermalKills: Boolean = true,
    val iosUnhandledExceptions: Boolean = true,
    val iosSignals: Boolean = true,
    val iosCppExceptions: Boolean = true,
    val iosMachExceptions: Boolean = true,
    val iosUnhandledRejections: Boolean = true,

    val jsUnhandledExceptions: Boolean = true,
    val jsUnhandledRejections: Boolean = true,
) {

    public constructor(
        unhandledExceptions: Boolean = true,
        unhandledRejections: Boolean = true,
        nativeCrashes: Boolean = true,
        appHangs: Boolean = true,
    ) : this(unhandledExceptions, unhandledRejections, nativeCrashes, appHangs, appHangs)
}