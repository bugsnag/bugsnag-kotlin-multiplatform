package com.bugsnag.kmp

import android.content.Context
import com.bugsnag.android.Bugsnag as PlatformBugsnag

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        PlatformBugsnag.start(configuration.androidContext, configuration.native)
    }

    public inline fun start(
        androidContext: Context,
        configure: Configuration.() -> Unit,
    ) {
        val configuration = Configuration(androidContext)
        configuration.configure()
        start(configuration)
    }

    public actual fun isStarted(): Boolean = PlatformBugsnag.isStarted()

    public actual fun notify(error: Throwable) {
        PlatformBugsnag.notify(error)
    }

    public actual fun leaveBreadcrumb(
        message: String,
        metadata: Map<String, Any>?,
        type: BreadcrumbType,
    ) {
        PlatformBugsnag.leaveBreadcrumb(message, metadata.orEmpty(), type.toPlatformType())
    }

    public actual fun addMetadata(
        section: String,
        key: String,
        value: Any?,
    ) {
        PlatformBugsnag.addMetadata(section, key, value)
    }

    public actual fun addMetadata(
        section: String,
        data: Map<String, Any>,
    ) {
        PlatformBugsnag.addMetadata(section, data)
    }

    public actual fun startSession() {
        PlatformBugsnag.startSession()
    }

    public actual fun pauseSession() {
        PlatformBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = PlatformBugsnag.resumeSession()

    public actual fun clearMetadata(section: String) {
        PlatformBugsnag.clearMetadata(section)
    }

    public actual fun clearMetadata(
        section: String,
        key: String,
    ) {
        PlatformBugsnag.clearMetadata(section, key)
    }

    public actual fun clearFeatureFlag(name: String) {
        PlatformBugsnag.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        PlatformBugsnag.clearFeatureFlags()
    }

    public actual fun addFeatureFlag(
        name: String,
        variant: String?,
    ) {
        PlatformBugsnag.addFeatureFlag(name, variant)
    }

    public actual val lastRunInfo: LastRunInfo?
        get() {
            val lastRunInfoAndroid = PlatformBugsnag.getLastRunInfo() ?: return null
            return LastRunInfo(
                lastRunInfoAndroid.consecutiveLaunchCrashes.toLong(),
                lastRunInfoAndroid.crashed,
                lastRunInfoAndroid.crashedDuringLaunch,
            )
        }

    public actual var context: String?
        get() = PlatformBugsnag.getContext()
        set(value) {
            PlatformBugsnag.setContext(value)
        }

    public actual var user: User
        get() {
            val androidUser = PlatformBugsnag.getUser()
            return User(
                id = androidUser.id,
                email = androidUser.email,
                name = androidUser.name,
            )
        }
        set(value) {
            PlatformBugsnag.setUser(value.id, value.email, value.name)
        }
}
