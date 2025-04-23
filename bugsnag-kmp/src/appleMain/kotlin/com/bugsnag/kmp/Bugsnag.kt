@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp

import com.bugsnag.cocoa.__KMP_configureCrashReportCallback
import kotlinx.cinterop.ExperimentalForeignApi
import com.bugsnag.cocoa.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        __KMP_configureCrashReportCallback(configuration.native)

        PlatformBugsnag.startWithConfiguration(configuration.native)
        installUncaughtExceptionHandler()
    }

    public actual fun isStarted(): Boolean = PlatformBugsnag.isStarted()

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        PlatformBugsnag.addMetadata(value, key, section)
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        @Suppress("UNCHECKED_CAST")
        PlatformBugsnag.addMetadata(data as Map<Any?, *>, section)
    }

    public actual fun leaveBreadcrumb(
        message: String,
        metadata: Map<String, Any>?,
        type: BreadcrumbType,
    ) {
        @Suppress("UNCHECKED_CAST")
        val metadataMap = metadata?.let { it as Map<Any?, *> }
        PlatformBugsnag.leaveBreadcrumbWithMessage(message, metadataMap, type.toPlatformType())
    }

    public actual fun startSession() {
        PlatformBugsnag.startSession()
    }

    public actual fun pauseSession() {
        PlatformBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = PlatformBugsnag.resumeSession()

    public actual fun clearMetadata(section: String) {
        PlatformBugsnag.clearMetadataFromSection(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        PlatformBugsnag.clearMetadataFromSection(section, key)
    }

    public actual fun clearFeatureFlag(name: String) {
        PlatformBugsnag.clearFeatureFlagWithName(name)
    }

    public actual fun clearFeatureFlags() {
        PlatformBugsnag.clearFeatureFlags()
    }

    public actual fun addFeatureFlag(name: String, variant: String?) {
        PlatformBugsnag.addFeatureFlagWithName(name, variant)
    }

    public actual val lastRunInfo: LastRunInfo?
        get() {
            val lastRunInfoAndroid = PlatformBugsnag.lastRunInfo ?: return null
            return LastRunInfo(
                lastRunInfoAndroid.consecutiveLaunchCrashes.toLong(),
                lastRunInfoAndroid.crashed,
                lastRunInfoAndroid.crashedDuringLaunch,
            )
        }

    public actual var context: String?
        get() = PlatformBugsnag.context()
        set(value) {
            PlatformBugsnag.setContext(value)
        }

    public actual var user: User
        get() {
            val appleUser = PlatformBugsnag.user()
            return User(
                id = appleUser.id,
                email = appleUser.email,
                name = appleUser.name,
            )
        }
        set(value) {
            PlatformBugsnag.setUser(value.id, value.email, value.name)
        }
}
