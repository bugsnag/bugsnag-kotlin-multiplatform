package com.bugsnag.kmp

import kotlin.jvm.JvmOverloads

/**
 * Information about the current user of your application.
 */
public class User @JvmOverloads public constructor(
    /**
     * @return the user ID, by default a UUID generated on installation
     */
    public val id: String? = null,

    /**
     * @return the user's name, if available
     */
    public val name: String? = null,

    /**
     * @return the user's email, if available
     */
    public val email: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as User

        if (id != other.id) return false
        if (email != other.email) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "User(id=$id, email=$email, name=$name)"
    }
}
