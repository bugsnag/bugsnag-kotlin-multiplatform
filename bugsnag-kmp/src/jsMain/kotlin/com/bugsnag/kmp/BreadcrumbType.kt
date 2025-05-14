package com.bugsnag.kmp

internal fun BreadcrumbType.toPlatformType(): String =
    when (this) {
        BreadcrumbType.ERROR -> "error"
        BreadcrumbType.LOG -> "log"
        BreadcrumbType.MANUAL -> "manual"
        BreadcrumbType.NAVIGATION -> "navigation"
        BreadcrumbType.PROCESS -> "process"
        BreadcrumbType.REQUEST -> "request"
        BreadcrumbType.STATE -> "state"
        BreadcrumbType.USER -> "user"
    }
