@file:JvmName("CommonConfigurationKt")

package com.bugsnag.kmp

import kotlin.jvm.JvmName

public expect class PlatformConfiguration

public expect class Configuration : PlatformWrapper<PlatformConfiguration> {
    public override val native: PlatformConfiguration

    /**
     * Retrieves and changes the API key used for events sent to Bugsnag.
     */
    public var apiKey: String

    /**
     * Set the application version sent to Bugsnag. This is typically detected in a platform-specific way, but
     * can be overwritten here if required.
     */
    public var appVersion: String?

    /**
     * Sets whether or not Bugsnag should automatically capture and report User sessions (typically whenever
     * the app enters the foreground).
     *
     * By default this behavior is enabled.
     */
    public var autoTrackSessions: Boolean

    /**
     * Bugsnag uses the concept of "contexts" to help display and group your errors. Contexts
     * represent what was happening in your application at the time an error occurs.
     *
     * In an android app the "context" is automatically set as the foreground Activity.
     * If you would like to set this value manually, you should alter this property.
     */
    public var context: String?

    /**
     * By default, Bugsnag will be notified of events that happen in any releaseStage.
     * If you would like to change which release stages notify Bugsnag you can set this property.
     */
    public var enabledReleaseStages: Set<String>?

    /**
     * Sets the threshold in milliseconds for an uncaught error to be considered as a crash on
     * launch. If a crash is detected on launch, Bugsnag will attempt to send the most recent
     * event synchronously.
     *
     * By default, this value is set at 5,000ms. Setting the value to 0 will count all crashes
     * as launch crashes until markLaunchCompleted() is called.
     */
    public var launchDurationMillis: Long

    /**
     * Sets the maximum number of breadcrumbs which will be stored. Once the threshold is reached,
     * the oldest breadcrumbs will be deleted.
     *
     * By default, 100 breadcrumbs are stored: this can be amended up to a maximum of 500.
     */
    public var maxBreadcrumbs: Int

    /**
     * If you would like to distinguish between errors that happen in different stages of the
     * application release process (development, production, etc) you can set the releaseStage
     * that is reported to Bugsnag.
     *
     * If you are running a debug build, we'll automatically set this to "development",
     * otherwise it is set to "production". You can control whether events are sent for
     * specific release stages using the enabledReleaseStages option.
     */
    public var releaseStage: String?

    /**
     * Returns the currently set User information.
     * Sets the user associated with the event.
     */
    public var user: User?

    /**
     * Sets which values should be removed from any Metadata objects before
     * sending them to Bugsnag. Use this if you want to ensure you don't send
     * sensitive data such as passwords, and credit card numbers to our
     * servers. Any keys which contain these strings will be filtered.
     *
     * By default, redactedKeys is set to "password"
     */
    public fun addRedactedKeys(redactedKeys: Collection<String>)
    public fun addRedactedKeys(vararg redactedKeys: String)

    /**
     * Add a single feature flag with an optional variant. If there is an existing feature
     * flag with the same name, it will be overwritten with the new variant. If the variant is
     * {@code null} this method has the same behaviour as {@link #addFeatureFlag(String)}.
     *
     * @param name    the name of the feature flag to add
     * @param variant the variant to set the feature flag to, or {@code null} to specify a feature
     *                flag with no variant
     */
    public fun addFeatureFlag(name: String, variant: String? = null)

    /**
     * Remove a single feature flag regardless of its current status. This will stop the specified
     * feature flag from being reported. If the named feature flag does not exist this will
     * have no effect.
     *
     * @param name the name of the feature flag to remove
     */
    public fun clearFeatureFlag(name: String)

    /**
     * Clear all of the feature flags. This will stop all feature flags from being reported.
     */
    public fun clearFeatureFlags()

    /**
     * Adds a map of multiple metadata key-value pairs to the specified section.
     */
    public fun addMetadata(section: String, data: Map<String, Any>)

    /**
     * Adds the specified key and value in the specified section. The value can be of
     * any primitive type or a collection such as a map, set or array.
     */
    public fun addMetadata(section: String, key: String, value: Any?)

    /**
     * Removes all the data from the specified section.
     */
    public fun clearMetadata(section: String)

    /**
     * Removes data with the specified key from the specified section.
     */
    public fun clearMetadata(section: String, key: String)

    /**
     * Bugsnag will automatically detect different types of error in your application.
     * If you wish to control exactly which types are enabled, set this property.
     */
    public fun setEnabledErrorTypes(types: EnabledErrorTypes)

    /**
     * Set the endpoints to send data to. By default we'll send error reports to
     * https://notify.bugsnag.com, and sessions to https://sessions.bugsnag.com, but you can
     * override this if you are using Bugsnag Enterprise to point to your own Bugsnag endpoints.
     */
    public fun setEndpoints(
        notify: String = "https://notify.bugsnag.com",
        sessions: String = "https://sessions.bugsnag.com",
    )
}

public inline fun Configuration.setEnabledErrorTypes(configure: EnabledErrorTypes.() -> Unit) {
    val enabledErrorTypes = EnabledErrorTypes()
    enabledErrorTypes.configure()
    setEnabledErrorTypes(enabledErrorTypes)
}
