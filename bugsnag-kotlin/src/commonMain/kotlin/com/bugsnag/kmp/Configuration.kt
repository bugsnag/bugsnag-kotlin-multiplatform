package com.bugsnag.kmp

public expect class PlatformConfiguration

public expect class Configuration : PlatformWrapper<PlatformConfiguration> {
    public override val native: PlatformConfiguration

    /**
     * Retrieves and changes the API key used for events sent to Bugsnag.
     */
    public var apiKey: String

    /**
     * Set the application version sent to Bugsnag. We'll automatically pull your app version
     * from the versionName field in your AndroidManifest.xml file.
     */
    public var appVersion: String?

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
     * Sets whether or not Bugsnag should automatically capture and report User sessions whenever
     * the app enters the foreground.
     *
     * By default this behavior is enabled.
     */
    public var autoTrackSessions: Boolean

    /**
     * Adds the specified key and value in the specified section. The value can be of
     * any primitive type or a collection such as a map, set or array.
     */
    public fun addMetadata(section: String, key: String, value: Any?)

    /**
     * Adds a map of multiple metadata key-value pairs to the specified section.
     */
    public fun addMetadata(section: String, data: Map<String, Any>)

    /**
     * Removes all the data from the specified section.
     */
    public fun clearMetadata(section: String)

    /**
     * Removes data with the specified key from the specified section.
     */
    public fun clearMetadata(section: String, key: String)

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
     * Add a single feature flag with an optional variant. If there is an existing feature
     * flag with the same name, it will be overwritten with the new variant. If the variant is
     * {@code null} this method has the same behaviour as {@link #addFeatureFlag(String)}.
     *
     * @param name    the name of the feature flag to add
     * @param variant the variant to set the feature flag to, or {@code null} to specify a feature
     *                flag with no variant
     */
    public fun addFeatureFlag(name: String, variant: String? = null)
    public var context: String?
}
