package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import com.bugsnag.cocoa.BSGSeverity as AppleSeverity

@OptIn(ExperimentalForeignApi::class)
internal fun Severity.toNativePlatformType(): AppleSeverity = when (this) {
    Severity.ERROR -> AppleSeverity.BSGSeverityError
    Severity.INFO -> AppleSeverity.BSGSeverityInfo
    Severity.WARNING -> AppleSeverity.BSGSeverityWarning
}

@OptIn(ExperimentalForeignApi::class)
internal fun AppleSeverity.toPlatformType(): Severity = when (this) {
    AppleSeverity.BSGSeverityError -> Severity.ERROR
    AppleSeverity.BSGSeverityInfo -> Severity.INFO
    AppleSeverity.BSGSeverityWarning -> Severity.WARNING
    else -> Severity.ERROR
}
