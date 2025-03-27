package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.Configuration
import kotlinx.coroutines.CoroutineScope

expect object Platform {
    val coroutineScope: CoroutineScope

    suspend fun loadFixtureConfiguration()

    fun configureEndpoints(notify: String, sessions: String)
    fun createBaseConfiguration(apiKey: String): Configuration

    suspend fun nextCommand(): Command?

    suspend fun log(message: String)
    suspend fun logError(message: String, ex: Exception?)
}
