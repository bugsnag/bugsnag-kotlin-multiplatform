package com.bugsnag.kmp

internal fun <V> Map<*, V>.convertToDynamic(): dynamic {
    val dynamicData = Any().asDynamic()
    forEach { (key, value) ->
        dynamicData[key] = value.toSafeMetadata()
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
    is List<*> -> map { it.toSafeMetadata() }.toTypedArray()
    is Array<*> -> Array(size) { index -> this[index].toSafeMetadata() }
    is String -> this
    is Byte -> this
    else -> this.toString()
}
