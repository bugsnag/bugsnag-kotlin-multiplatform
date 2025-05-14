package com.bugsnag.kmp

public data class EnabledErrorTypes(
    var androidAnrs: Boolean = true,
    var androidNdkCrashes: Boolean = true,
    var androidUnhandledExceptions: Boolean = true,
    var androidUnhandledRejections: Boolean = true,
    var iosAppHangs: Boolean = true,
    var iosOoms: Boolean = true,
    var iosThermalKills: Boolean = true,
    var iosUnhandledExceptions: Boolean = true,
    var iosSignals: Boolean = true,
    var iosCppExceptions: Boolean = true,
    var iosMachExceptions: Boolean = true,
    var iosUnhandledRejections: Boolean = true,
    var jsUnhandledExceptions: Boolean = true,
    var jsUnhandledRejections: Boolean = true,
) {
    public constructor(
        unhandledExceptions: Boolean = true,
        unhandledRejections: Boolean = true,
        /**
         * Whether to enable detection and reporting of native crashes. Equivalent to setting
         * [androidNdkCrashes], [iosSignals], [iosOoms], [iosThermalKills], and [iosMachExceptions].
         */
        nativeCrashes: Boolean = true,
        /**
         * Whether to enable detection and reporting of app hangs/ANRs.
         * Equivalent to setting both [androidAnrs] and [iosAppHangs].
         */
        appHangs: Boolean = true,
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
