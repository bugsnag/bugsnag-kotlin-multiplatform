package com.bugsnag.kmp

import com.bugsnag.android.BreadcrumbType as PlatformBreadcrumbType

internal fun BreadcrumbType.toPlatformType(): PlatformBreadcrumbType =
    when (this) {
        BreadcrumbType.ERROR -> PlatformBreadcrumbType.ERROR
        BreadcrumbType.LOG -> PlatformBreadcrumbType.LOG
        BreadcrumbType.MANUAL -> PlatformBreadcrumbType.MANUAL
        BreadcrumbType.NAVIGATION -> PlatformBreadcrumbType.NAVIGATION
        BreadcrumbType.PROCESS -> PlatformBreadcrumbType.PROCESS
        BreadcrumbType.REQUEST -> PlatformBreadcrumbType.REQUEST
        BreadcrumbType.STATE -> PlatformBreadcrumbType.STATE
        BreadcrumbType.USER -> PlatformBreadcrumbType.USER
    }
