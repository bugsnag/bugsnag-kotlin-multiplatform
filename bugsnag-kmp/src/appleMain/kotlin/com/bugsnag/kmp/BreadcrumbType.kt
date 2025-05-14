package com.bugsnag.kmp

import kotlinx.cinterop.ExperimentalForeignApi
import com.bugsnag.cocoa.BSGBreadcrumbType as PlatformBreadcrumbType

@OptIn(ExperimentalForeignApi::class)
internal fun BreadcrumbType.toPlatformType(): PlatformBreadcrumbType =
    when (this) {
        BreadcrumbType.ERROR -> PlatformBreadcrumbType.BSGBreadcrumbTypeError
        BreadcrumbType.LOG -> PlatformBreadcrumbType.BSGBreadcrumbTypeLog
        BreadcrumbType.MANUAL -> PlatformBreadcrumbType.BSGBreadcrumbTypeManual
        BreadcrumbType.NAVIGATION -> PlatformBreadcrumbType.BSGBreadcrumbTypeNavigation
        BreadcrumbType.PROCESS -> PlatformBreadcrumbType.BSGBreadcrumbTypeProcess
        BreadcrumbType.REQUEST -> PlatformBreadcrumbType.BSGBreadcrumbTypeRequest
        BreadcrumbType.STATE -> PlatformBreadcrumbType.BSGBreadcrumbTypeState
        BreadcrumbType.USER -> PlatformBreadcrumbType.BSGBreadcrumbTypeUser
    }
