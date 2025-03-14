package com.bugsnag.kmp

/**
 * Marker interface for Kotlin common classes which wrap a native implementation.
 */
public interface PlatformWrapper<T> {
    /**
     * The underlying platform object being wrapped
     */
    public val native: T
}
