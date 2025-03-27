package com.bugsnag.kmp.mazerunner

import com.bugsnag.cocoa.BugsnagEndpointConfiguration
import com.bugsnag.kmp.Configuration
import com.bugsnag.kmp.PlatformConfiguration
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

@OptIn(ExperimentalForeignApi::class)
actual object Platform {
    private var fixtureConfig: MazeRunnerConfig? = null

    private var notifyEndpoint: String? = null
    private var sessionEndpoint: String? = null

    @OptIn(DelicateCoroutinesApi::class)
    actual val coroutineScope: CoroutineScope get() = GlobalScope

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

        MazeLogger.log("attempting to load fixture config: '$configUrl'")

        repeat(60) { attempt ->
            try {
                val data = NSData.dataWithContentsOfURL(configUrl)!!
                val json = data.readAsString()!!

                fixtureConfig = Json.decodeFromString(json)
                return@withContext
            } catch (ex: Exception) {
                MazeLogger.log("couldn't load config (attempt $attempt)", ex)
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
            val url = NSURL.URLWithString("$endpoint/command")!!
            MazeLogger.log("attempt to fetch command from: $url")
            val request = NSURLRequest(url)
            val (_, data) = session.request(request)

            if (data == null) {
                MazeLogger.log("no result returned from $url")
                return null
            }

            val jsonString = data.readAsString() ?: return null
            try {
                return Json.decodeFromString<Command>(jsonString)
            } catch (jsone: Exception) {
                MazeLogger.log("could not parse command '$jsonString'", jsone)
            }
        } catch (ex: Exception) {
            MazeLogger.log("could not fetch command from $endpoint", ex)
        }

        return null
    }

    actual suspend fun log(message: String) {
        NSLog("Bugsnag MazeRunner: %s", message)
    }

    actual suspend fun logError(message: String, ex: Exception?) {
        NSLog("Bugsnag MazeRunner: %s", message)
        ex?.let { NSLog("%s", it.stackTraceToString()) }
    }
}
