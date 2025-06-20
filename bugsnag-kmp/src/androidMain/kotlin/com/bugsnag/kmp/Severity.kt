package com.bugsnag.kmp

import com.bugsnag.android.Severity as AndroidSeverity

internal fun Severity.toNativePlatformType(): AndroidSeverity = when (this) {
    Severity.ERROR -> AndroidSeverity.ERROR
    Severity.INFO -> AndroidSeverity.INFO
    Severity.WARNING -> AndroidSeverity.WARNING
}

internal fun AndroidSeverity.toPlatformType(): Severity = when (this) {
    AndroidSeverity.ERROR -> Severity.ERROR
    AndroidSeverity.INFO -> Severity.INFO
    AndroidSeverity.WARNING -> Severity.WARNING
}
