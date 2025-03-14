@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.Configuration
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.serialization.json.Json
import platform.Foundation.NSLog
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLSession

actual object Platform {
    private var fixtureConfig: MazeRunnerConfig? = null

    actual suspend fun loadFixtureConfiguration() {
    }

    actual fun createBaseConfiguration(apiKey: String): Configuration {
        val configuration = Configuration()
        configuration.apiKey = apiKey
        return configuration
    }

    actual suspend fun nextCommand(): Command? {
        val endpoint = fixtureConfig?.endpointURL ?: return null

        val session = NSURLSession.sharedSession()

        try {
            val request = NSURLRequest(NSURL.URLWithString(endpoint)!!)
            val (_, data) = session.request(request)

            if (data == null) {
                return null
            }

            val jsonString = data.readAsString() ?: return null
            return Json.decodeFromString<Command>(jsonString)
        } catch (ex: Exception) {
            logError("could not fetch command from $endpoint", ex)
        }

        return null
    }

    actual suspend fun log(message: String) {
        NSLog("%s", message)
    }

    actual suspend fun logError(message: String, ex: Exception?) {
        NSLog("%s", message)
        ex?.let { NSLog("%s", it.stackTraceToString()) }
    }
}
