package com.bugsnag.kmp

import kotlin.jvm.JvmOverloads

/**
 * Information about the current user of your application.
 */
public class User @JvmOverloads internal constructor(
    /**
     * @return the user ID, by default a UUID generated on installation
     */
    public val id: String? = null,

    /**
     * @return the user's email, if available
     */
    public val email: String? = null,

    /**
     * @return the user's name, if available
     */
    public val name: String? = null
)
