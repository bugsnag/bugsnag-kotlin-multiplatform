@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp.mazerunner

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLResponse
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithRequest
import platform.posix.memcpy
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WrappedNSError(error: NSError) : Exception(error.description())

suspend fun NSURLSession.request(urlRequest: NSURLRequest): Pair<NSURLResponse, NSData?> =
    suspendCoroutine { cont ->
        dataTaskWithRequest(urlRequest) { data, response, error ->
            if (error != null) {
                cont.resumeWithException(WrappedNSError(error))
            } else if (response != null) {
                cont.resume(response to data)
            } else {
                cont.resumeWithException(NullPointerException("no response or data returned"))
            }
        }.resume()
    }

fun NSData.readAsString(): String {
    val buffer = ByteArray(length.toInt())
    buffer.usePinned {
        memcpy(it.addressOf(0), bytes, length)
    }

    return buffer.toKString()
}
