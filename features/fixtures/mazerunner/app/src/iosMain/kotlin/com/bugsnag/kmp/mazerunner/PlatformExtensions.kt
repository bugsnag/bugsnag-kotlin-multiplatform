@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp.mazerunner

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLResponse
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithRequest
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
            }
        }
    }

fun NSData.readAsString(): String? {
    return bytes?.reinterpret<ByteVar>()?.toKString()
}
