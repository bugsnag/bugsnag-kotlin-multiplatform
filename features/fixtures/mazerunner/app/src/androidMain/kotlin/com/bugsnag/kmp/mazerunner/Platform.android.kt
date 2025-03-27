package com.bugsnag.kmp.mazerunner

import android.app.Application
import android.util.Log
import com.bugsnag.android.EndpointConfiguration
import com.bugsnag.kmp.Configuration
import com.bugsnag.kmp.PlatformConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File
import java.net.URL
import kotlin.time.Duration.Companion.milliseconds

actual object Platform {
    lateinit var application: Application

    private var mazeRunnerConfig: MazeRunnerConfig? = null

    private var notifyEndpoint: String? = null
    private var sessionEndpoint: String? = null

    @OptIn(DelicateCoroutinesApi::class)
    actual val coroutineScope: CoroutineScope get() = GlobalScope

    actual fun configureEndpoints(notify: String, sessions: String) {
        notifyEndpoint = notify
        sessionEndpoint = sessions
    }

    actual suspend fun loadFixtureConfiguration() {
        withContext(Dispatchers.IO) {
            val externalFilesDir = application.getExternalFilesDir(null)
            val configFile = File(externalFilesDir, "fixture_config.json")
            MazeLogger.log("attempting to load fixture config: '$configFile'")

            val timeout = System.currentTimeMillis() + CONFIG_READ_TIMEOUT
            while (System.currentTimeMillis() < timeout) {
                if (configFile.exists() && configFile.canRead()) {
                    try {
                        mazeRunnerConfig = Json.decodeFromString(configFile.readText())
                        break
                    } catch (ex: Exception) {
                        MazeLogger.log("failed to load ${configFile.name}", ex)
                    }
                }

                delay(50.milliseconds)
            }
        }

        if (mazeRunnerConfig == null) {
            MazeLogger.log("Failed to read Maze Runner address from config file, defaulting to legacy BrowserStack address")
            mazeRunnerConfig = MazeRunnerConfig("bs-local.com:9339")
        }
    }

    actual fun createBaseConfiguration(apiKey: String): Configuration {
        val platformConfiguration = PlatformConfiguration(apiKey)
        if (!notifyEndpoint.isNullOrEmpty() && !sessionEndpoint.isNullOrEmpty()) {
            platformConfiguration.endpoints = EndpointConfiguration(
                notifyEndpoint!!,
                sessionEndpoint!!,
            )
        }

        platformConfiguration.logger = AndroidLogger

        return Configuration(application, platformConfiguration)
    }

    actual suspend fun log(message: String) {
        Log.i("Bugsnag MazeRunner", message)
    }

    actual suspend fun logError(message: String, ex: Exception?) {
        Log.w("Bugsnag MazeRunner", message, ex)
    }

    @ExperimentalSerializationApi
    actual suspend fun nextCommand(): Command? = withContext(Dispatchers.IO) {
        val endpoint = mazeRunnerConfig?.endpointURL ?: return@withContext null
        try {
            return@withContext URL("$endpoint/command").openStream().use {
                Json.decodeFromStream<Command>(it)
            }
        } catch (ex: Exception) {
            MazeLogger.log("could not retrieve next command from $endpoint", ex)
            return@withContext null
        }
    }

    const val CONFIG_READ_TIMEOUT = 5000
}
