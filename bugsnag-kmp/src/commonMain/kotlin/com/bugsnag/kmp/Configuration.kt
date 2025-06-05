@file:JvmName("CommonConfigurationKt")

package com.bugsnag.kmp

import kotlin.jvm.JvmName

public expect class PlatformConfiguration

public expect class Configuration : PlatformWrapper<PlatformConfiguration> {
    public override val native: PlatformConfiguration

    /**
     * The API key used for authentication with the Bugsnag error reporting service.
     * This value can be found in your Bugsnag project settings.
     */
    public var apiKey: String

    /**
     * The version number or unique identifier of your application.
     * This is used to track error rates across different app versions.
     * If not set, will use platform-specific defaults (e.g. versionName on Android).
     */
    public var appVersion: String?

    /**
     * Sets whether or not Bugsnag should automatically capture and report user sessions. The exact
     * behaviour is platform dependant.
     */
    public var autoTrackSessions: Boolean

    /**
     * A string representing what was happening in your application at the time an error occurs.
     * This can be an activity name, screen name, or route - used for error grouping and filtering.
     */
    public var context: String?

    /**
     * The set of release stages that will send errors to Bugsnag.
     * If `null`, all release stages will notify Bugsnag.
     *
     * @see [releaseStage]
     */
    public var enabledReleaseStages: Set<String>?

    /**
     * The duration in milliseconds that Bugsnag will track app launch crashes.
     * Any crashes within this time window after app launch will be tagged as launch crashes.
     */
    public var launchDurationMillis: Long

    /**
     * The maximum number of breadcrumbs to store and upload with error reports.
     * Breadcrumbs are used to track user actions leading up to a crash.
     */
    public var maxBreadcrumbs: Int

    /**
     * The current release stage of your application.  Common values are: "development",
     * "staging", "production". This defaults to `"development"` or `"production"` based on
     * platform specific logic.
     *
     * @see [enabledReleaseStage]
     */
    public var releaseStage: String?

    /**
     * Information about the current application user.
     * Setting this will associate errors with specific users.
     */
    public var user: User?

    /**
     * Adds keys to be redacted from error reports for security/privacy.
     * Any keys matching these values (case-insensitive) will be redacted
     * from metadata, breadcrumbs and other locations.
     * Adds keys to be redacted from error reports for security/privacy.
     * Any metadata keys matching these values (case-insensitive) will be redacted.
     */
    public fun addRedactedKeys(redactedKeys: Collection<String>)

    /**
     * Adds keys to be redacted from error reports for security/privacy.
     * Any keys matching these values (case-insensitive) will be redacted
     * from metadata, breadcrumbs and other locations.
     * Adds keys to be redacted from error reports for security/privacy.
     * Any metadata keys matching these values (case-insensitive) will be redacted.
     */
    public fun addRedactedKeys(vararg redactedKeys: String)

    /**
     * Add a single feature flag with an optional variant.
     *
     * @param name the unique identifier of the feature flag
     * @param variant optional variant name for A/B testing different versions
     * @see [clearFeatureFlag]
     * @see [clearFeatureFlags]
     */
    public fun addFeatureFlag(name: String, variant: String? = null)

    /**
     * Removes a specific feature flag from tracking.
     *
     * @param name the name of the feature flag to remove
     * @see [addFeatureFlag]
     * @see [clearFeatureFlags]
     */
    public fun clearFeatureFlag(name: String)

    /**
     * Removes all currently set feature flags.
     *
     * @see [addFeatureFlag]
     * @see [clearFeatureFlag]
     */
    public fun clearFeatureFlags()

    /**
     * Adds custom diagnostic data to a named section of your error reports.
     *
     * @param section the name of the metadata section
     * @param data key-value pairs to add to the section
     * @see [clearMetadata]
     */
    public fun addMetadata(section: String, data: Map<String, Any>)

    /**
     * Adds a single piece of custom diagnostic data to a section.
     *
     * @param section the name of the metadata section
     * @param key the key for this metadata value
     * @param value the value to store (can be null)
     * @see [clearMetadata]
     */
    public fun addMetadata(section: String, key: String, value: Any?)

    /**
     * Removes all custom diagnostic data from the specified section.
     * @param section the name of the section to clear
     * @see [addMetadata]
     */
    public fun clearMetadata(section: String)

    /**
     * Removes a specific piece of custom diagnostic data.
     *
     * @param section the name of the section containing the data
     * @param key the key to remove
     * @see [addMetadata]
     */
    public fun clearMetadata(section: String, key: String)

    /**
     * Configures which types of errors should be reported to Bugsnag.
     * Use this to enable/disable reporting of crashes, ANRs, etc.
     */
    public fun setEnabledErrorTypes(types: EnabledErrorTypes)

    /**
     * Configures custom endpoints for error and session reporting.
     * Only needed when using an on-premise Bugsnag installation.
     *
     * @param notify the URL for reporting errors
     * @param sessions the URL for reporting sessions
     */
    public fun setEndpoints(
        notify: String = "https://notify.bugsnag.com",
        sessions: String = "https://sessions.bugsnag.com",
    )
}

/**
 * Convenience function to [configure] the enabled error types.
 */
public inline fun Configuration.setEnabledErrorTypes(configure: EnabledErrorTypes.() -> Unit) {
    val enabledErrorTypes = EnabledErrorTypes()
    enabledErrorTypes.configure()
    setEnabledErrorTypes(enabledErrorTypes)
}
