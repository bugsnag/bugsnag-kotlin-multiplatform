@file:OptIn(ExperimentalForeignApi::class)

package com.bugsnag.kmp.mazerunner

import com.bugsnag.cocoa.BugsnagEndpointConfiguration
import com.bugsnag.kmp.Configuration
import com.bugsnag.kmp.PlatformConfiguration
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSLog
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLSession
import platform.Foundation.NSUserDomainMask
import platform.Foundation.dataWithContentsOfURL
import kotlin.time.Duration.Companion.seconds

actual object Platform {
    private var fixtureConfig: MazeRunnerConfig? = null

    private var notifyEndpoint: String? = null
    private var sessionEndpoint: String? = null

    actual fun configureEndpoints(notify: String, sessions: String) {
        notifyEndpoint = notify
        sessionEndpoint = sessions
    }

    actual suspend fun loadFixtureConfiguration() = withContext(Dispatchers.IO) {
        val documentsUrl = NSFileManager
            .defaultManager()
            .URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
            .first() as NSURL

        val configUrl = NSURL.fileURLWithPath("fixture_config", relativeToURL = documentsUrl)
            .URLByAppendingPathExtension("json")!!

        log("attempting to load fixture config: '$configUrl'")

        repeat(60) { attempt ->
            try {
                val data = NSData.dataWithContentsOfURL(configUrl)!!
                val json = data.readAsString()!!

                fixtureConfig = Json.decodeFromString(json)
                return@withContext
            } catch (ex: Exception) {
                logError("couldn't load config (attempt $attempt)", ex)
            }

            delay(1.seconds)
        }
    }

    actual fun createBaseConfiguration(apiKey: String): Configuration {
        val platformConfiguration = PlatformConfiguration(apiKey)
        if (!notifyEndpoint.isNullOrEmpty() && !sessionEndpoint.isNullOrEmpty()) {
            platformConfiguration.endpoints = BugsnagEndpointConfiguration(
                notifyEndpoint!!,
                sessionEndpoint!!,
            )
        }

        return Configuration(platformConfiguration)
    }

    actual suspend fun nextCommand(): Command? {
        val endpoint = fixtureConfig?.endpointURL ?: return null

        val session = NSURLSession.sharedSession()

        try {
            val request = NSURLRequest(NSURL.URLWithString("$endpoint/command")!!)
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
