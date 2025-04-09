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
        nativeCrashes: Boolean = true, // ndkCrashes, iosSignals, machExceptions
        appHangs: Boolean = true, // ANRs + AppHangs
    ) : this(
        androidAnrs = appHangs,
        androidNdkCrashes = nativeCrashes,
        androidUnhandledExceptions = unhandledExceptions,
        androidUnhandledRejections = unhandledRejections,

        iosAppHangs = appHangs,
        iosOoms = nativeCrashes,
        iosThermalKills = nativeCrashes,
        iosUnhandledExceptions = unhandledExceptions,
        iosSignals = nativeCrashes,
        iosCppExceptions = unhandledExceptions,
        iosMachExceptions = nativeCrashes,
        iosUnhandledRejections = unhandledRejections,

        jsUnhandledExceptions = unhandledExceptions,
        jsUnhandledRejections = unhandledRejections,
    )
}
