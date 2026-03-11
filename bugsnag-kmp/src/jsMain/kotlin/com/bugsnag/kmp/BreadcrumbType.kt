package com.bugsnag.kmp

internal fun BreadcrumbType.toPlatformType(): String = when (this) {
    BreadcrumbType.ERROR -> "error"
    BreadcrumbType.LOG -> "log"
    BreadcrumbType.MANUAL -> "manual"
    BreadcrumbType.NAVIGATION -> "navigation"
    BreadcrumbType.PROCESS -> "process"
    BreadcrumbType.REQUEST -> "request"
    BreadcrumbType.STATE -> "state"
    BreadcrumbType.USER -> "user"
}

internal fun breadcrumbTypeFromPlatformType(jsType: String): BreadcrumbType? = when (jsType) {
    "error" -> BreadcrumbType.ERROR
    "log" -> BreadcrumbType.LOG
    "manual" -> BreadcrumbType.MANUAL
    "navigation" -> BreadcrumbType.NAVIGATION
    "process" -> BreadcrumbType.PROCESS
    "request" -> BreadcrumbType.REQUEST
    "state" -> BreadcrumbType.STATE
    "user" -> BreadcrumbType.USER
    else -> null
}
