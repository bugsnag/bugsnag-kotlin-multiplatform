package com.bugsnag.kmp

internal fun <V> Map<*, V>.convertToDynamic(): dynamic {
    val dynamicData = Any().asDynamic()
    forEach { (key, value) ->
        if (value is Map<*, *>) {
            @Suppress("UNCHECKED_CAST")
            dynamicData[key] = (value as Map<String, Any?>).convertToDynamic()
        } else if (key is String) {
            dynamicData[key] = value
        }
    }
    return dynamicData
}

internal fun Any?.toSafeMetadata(): Any? = when (this) {
    null -> null
    is Double -> this
    is Long -> this
    is Int -> this
    is Boolean -> this
    is Short -> this
    is Float -> this
    is Map<*, *> -> convertToDynamic()
    is List<*> -> {
        this.map { it.toSafeMetadata() }
    }

    is Array<*> -> {
        Array(size) { index -> this[index].toSafeMetadata() }
    }

    is String -> this
    is Byte -> this
    else -> this.toString()
}
