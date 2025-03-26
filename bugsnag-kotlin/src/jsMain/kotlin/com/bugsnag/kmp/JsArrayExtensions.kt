package com.bugsnag.kmp

@Suppress("NOTHING_TO_INLINE")
private inline fun <T> Array<T>.splice(start: Int, deleteCount: Int): Array<T> =
    asDynamic().splice(start, deleteCount).unsafeCast<Array<T>>()

@Suppress("NOTHING_TO_INLINE")
internal inline fun <T> Array<T>.add(item: T): Array<T> =
    asDynamic().push(item).unsafeCast<Array<T>>()

internal inline fun <T> Array<T>.removeFirst(predicate: (T) -> Boolean): Array<T> {
    val index = indexOfFirst(predicate)

    if (index < 0) {
        return this
    } else {
        return splice(index, 1)
    }
}
