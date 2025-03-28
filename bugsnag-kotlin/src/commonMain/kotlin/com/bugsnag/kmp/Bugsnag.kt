package com.bugsnag.kmp

public expect object Bugsnag {

    /**
     * Initialize the static Bugsnag client
     * @param config         a configuration for the Client
     */
    public fun start(configuration: Configuration)

    /**
     * Adds the specified key and value in the specified section. The value can be of
     * any primitive type or a collection such as a map, set or array.
     */
    public fun addMetadata( section: String, key: String, value: Any?)

    /**
     * Adds a map of multiple metadata key-value pairs to the specified section.
     */
    public fun addMetadata(section: String, data: Map<String, Any>)

    /**
     * Starts tracking a new session. You should disable automatic session tracking via
     * {@link Configuration#setAutoTrackSessions(boolean)} if you call this method.
     * <p/>
     * You should call this at the appropriate time in your application when you wish to start a
     * session. Any subsequent errors which occur in your application will still be reported to
     * Bugsnag but will not count towards your application's
     * <a href="https://docs.bugsnag.com/product/releases/releases-dashboard/#stability-score">
     * stability score</a>. This will start a new session even if there is already an existing
     * session; you should call {@link #resumeSession()} if you only want to start a session
     * when one doesn't already exist.
     *
     * @see #resumeSession()
     * @see #pauseSession()
     * @see Configuration#setAutoTrackSessions(boolean)
     */
    public fun startSession()

    /**
     * Pauses tracking of a session. You should disable automatic session tracking via
     * {@link Configuration#setAutoTrackSessions(boolean)} if you call this method.
     * <p/>
     * You should call this at the appropriate time in your application when you wish to pause a
     * session. Any subsequent errors which occur in your application will still be reported to
     * Bugsnag but will not count towards your application's
     * <a href="https://docs.bugsnag.com/product/releases/releases-dashboard/#stability-score">
     * stability score</a>. This can be advantageous if, for example, you do not wish the
     * stability score to include crashes in a background service.
     *
     * @see #startSession()
     * @see #resumeSession()
     * @see Configuration#setAutoTrackSessions(boolean)
     */
    public fun pauseSession()

    /**
     * Resumes a session which has previously been paused, or starts a new session if none exists.
     * If a session has already been resumed or started and has not been paused, calling this
     * method will have no effect. You should disable automatic session tracking via
     * {@link Configuration#setAutoTrackSessions(boolean)} if you call this method.
     * <p/>
     * It's important to note that sessions are stored in memory for the lifetime of the
     * application process and are not persisted on disk. Therefore calling this method on app
     * startup would start a new session, rather than continuing any previous session.
     * <p/>
     * You should call this at the appropriate time in your application when you wish to resume
     * a previously started session. Any subsequent errors which occur in your application will
     * still be reported to Bugsnag but will not count towards your application's
     * <a href="https://docs.bugsnag.com/product/releases/releases-dashboard/#stability-score">
     * stability score</a>.
     *
     * @return true if a previous session was resumed, false if a new session was started.
     * @see #startSession()
     * @see #pauseSession()
     * @see Configuration#setAutoTrackSessions(boolean)
     */
    public fun resumeSession(): Boolean

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
    public val lastRunInfo: LastRunInfo?
    public var context: String?
}
