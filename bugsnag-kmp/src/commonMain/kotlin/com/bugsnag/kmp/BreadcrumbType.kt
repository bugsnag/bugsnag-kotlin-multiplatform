package com.bugsnag.kmp

/**
 * Recognized types of breadcrumbs
 */
public enum class BreadcrumbType {
    /**
     * An error was sent to Bugsnag (internal use only)
     */
    ERROR,

    /**
     * A log message
     */
    LOG,

    /**
     * A manual invocation of `leaveBreadcrumb` (default)
     */
    MANUAL,

    /**
     * A navigation event, such as a window opening or closing
     */
    NAVIGATION,

    /**
     * A background process such as a database query
     */
    PROCESS,

    /**
     * A network request
     */
    REQUEST,

    /**
     * A change in application state, such as launch or memory warning
     */
    STATE,

    /**
     * A user action, such as tapping a button
     */
    USER,
}
