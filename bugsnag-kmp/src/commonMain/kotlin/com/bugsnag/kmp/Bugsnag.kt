package com.bugsnag.kmp

/**
 * The main entry point for interacting with the Bugsnag error monitoring SDK.
 *
 * Provides methods to initialize the SDK, report errors, manage sessions, add metadata,
 * leave breadcrumbs, and handle feature flags. This object is expected to be implemented
 * for each supported platform.
 *
 * Typical usage:
 * ```
 * Bugsnag.start(configuration)
 * Bugsnag.notify(RuntimeException("An error occurred"))
 * ```
 */
public expect object Bugsnag {

    /**
     * Initialize the Bugsnag client SDK
     *
     * @param configuration the configuration to start with
     */
    public fun start(configuration: Configuration)

    /**
     * Returns `true` if the Bugsnag client has been started, otherwise `false`. If this returns
     * `false`, then no errors will be reported and any calls to this class
     * (other than [start] and [isStarted]) will raise errors.
     */
    public fun isStarted(): Boolean

    /**
     * Send a non-fatal error to InsightHub.
     *
     * @param error the error to report
     */
    public fun notify(error: Throwable, onError: OnErrorCallback? = null)

    /**
     * Adds the specified key and value in the specified section. The value can be of
     * any primitive type or a collection such as a map, set or array.
     *
     * @param section the metadata section name
     * @param key the metadata key
     * @param value the value to store
     * @see [clearMetadata]
     */
    public fun addMetadata(section: String, key: String, value: Any?)

    /**
     * Add multiple metadata values to a section
     *
     * @param section the metadata section name
     * @param data key-value pairs of the metadata to add
     * @see [clearMetadata]
     */
    public fun addMetadata(section: String, data: Map<String, Any>)

    /**
     * Start tracking a new session. This will start a new session even if there is already an existing
     * session; you should call [resumeSession] if you only want to start a session
     * when one doesn't already exist.
     *
     * @see [resumeSession]
     * @see [pauseSession]
     */
    public fun startSession()

    /**
     * Pause tracking of the current session.Any subsequent errors which occur in your application
     * will still be reported to Bugsnag but will not count towards your application's
     * [stability score](https://docs.bugsnag.com/product/releases/releases-dashboard/#stability-score).
     * This can be advantageous if, for example, you do not wish the stability score to include crashes
     * in a background service.
     *
     * @see [startSession]
     * @see [resumeSession]
     */
    public fun pauseSession()

    /**
     * Resume tracking of a previously paused session. You should call this at the appropriate time
     * in your application when you wish to resume a previously started session. Any subsequent
     * errors which occur in your application will still be reported to Bugsnag but will not count
     * towards your application's
     * [stability score](https://docs.bugsnag.com/product/releases/releases-dashboard/#stability-score).
     *
     * @return `true` if a previous session was resumed, `false` if a new session started
     * @see [startSession]
     * @see [pauseSession]
     */
    public fun resumeSession(): Boolean

    /**
     * Leave a "breadcrumb" log message, representing an action that occurred
     * in your app, to aid with debugging.
     *
     * @param message a concise description of the event
     * @param metadata optional diagnostics data
     * @param type the type of breadcrumb
     */
    public fun leaveBreadcrumb(
        message: String,
        metadata: Map<String, Any>? = null,
        type: BreadcrumbType = BreadcrumbType.MANUAL,
    )

    /**
     * Remove all metadata from a section
     *
     * @param section the name of the section to clear
     * @see [addMetadata]
     */
    public fun clearMetadata(section: String)

    /**
     * Remove a specific metadata value from a specific section
     *
     * @param section the name of the section containing the key
     * @param key the key to remove
     * @see [addMetadata]
     */
    public fun clearMetadata(section: String, key: String)

    /**
     * Remove a single feature flag regardless of its current status.
     *
     * @param name the feature flag name to remove
     * @see [addFeatureFlag]
     * @see [clearFeatureFlags]
     */
    public fun clearFeatureFlag(name: String)

    /**
     * Remove all of the feature flags.
     *
     * @see [addFeatureFlag]
     * @see [clearFeatureFlag]
     */
    public fun clearFeatureFlags()

    /**
     * Add a single feature flag with an optional variant. If there is an existing feature
     * flag with the same name, it will be overwritten with the new variant.
     *
     * @param name the feature flag name
     * @param variant the variant to set the feature flag, or `null` to specify a feature flag with no variant
     * @see [clearFeatureFlag]
     * @see [clearFeatureFlags]
     */
    public fun addFeatureFlag(name: String, variant: String? = null)

    /**
     * Information about the previous app run
     */
    public val lastRunInfo: LastRunInfo?

    /**
     * The current context (e.g. activity name, route)
     * used for grouping errors
     */
    public var context: String?

    /**
     * The current application user
     */
    public var user: User
}
