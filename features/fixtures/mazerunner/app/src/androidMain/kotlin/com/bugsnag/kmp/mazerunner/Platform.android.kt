package com.bugsnag.kmp.mazerunner

import android.app.Application
import android.util.Log
import com.bugsnag.kmp.Configuration
import kotlinx.coroutines.Dispatchers
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

    actual suspend fun loadFixtureConfiguration() {
        withContext(Dispatchers.IO) {
            val externalFilesDir = application.getExternalFilesDir(null)
            val configFile = File(externalFilesDir, "fixture_config.json")
            log("attempting to load fixture config: '$configFile'")

            val timeout = System.currentTimeMillis() + CONFIG_READ_TIMEOUT
            while (System.currentTimeMillis() < timeout) {
                if (configFile.exists() && configFile.canRead()) {
                    try {
                        mazeRunnerConfig = Json.decodeFromString(configFile.readText())
                        break
                    } catch (ex: Exception) {
                        logError("failed to load ${configFile.name}", ex)
                    }
                }

                delay(50.milliseconds)
            }
        }

        if (mazeRunnerConfig == null) {
            log("Failed to read Maze Runner address from config file, defaulting to legacy BrowserStack address")
            mazeRunnerConfig = MazeRunnerConfig("bs-local.com:9339")
        }
    }

    actual fun createBaseConfiguration(apiKey: String): Configuration {
        val configuration = Configuration(application)
        configuration.apiKey = apiKey
        return configuration
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
            logError("could not retrieve next command from $endpoint", ex)
            return@withContext null
        }
    }

    const val CONFIG_READ_TIMEOUT = 5000
}
