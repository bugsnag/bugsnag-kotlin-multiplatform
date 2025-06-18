package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.Configuration
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.w3c.dom.url.URL
import kotlin.coroutines.CoroutineContext

actual object Platform {
    private var notifyEndpoint: String? = null
    private var sessionEndpoint: String? = null

    private val windowCoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        console.log("caught a coroutine exception", throwable)
        // the only way to get this out of the coroutine stack is to trapeze to a JS timeout
        window.setTimeout({ throw throwable }, 1)
    }

    actual val coroutineScope: CoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = windowCoroutineExceptionHandler + SupervisorJob() + Dispatchers.Main
    }

    actual fun configureEndpoints(notify: String, sessions: String) {
        notifyEndpoint = notify
        sessionEndpoint = sessions
    }

    actual fun createBaseConfiguration(apiKey: String): Configuration =
        Configuration(apiKey).apply {
            if (!notifyEndpoint.isNullOrEmpty() && !sessionEndpoint.isNullOrEmpty()) {
                val endpoints = Any().asDynamic()
                endpoints.notify = notifyEndpoint
                endpoints.sessions = sessionEndpoint
                native.asDynamic().endpoints = endpoints
            }
        }

    actual suspend fun nextCommand(): Command? {
        val pageUrl = URL(window.location.href)
        return Command(
            Command.ACTION_RUN_SCENARIO,
            requireNotNull(pageUrl.searchParams.get("SCENARIO")) { "missing 'SCENARIO' query parameter" },
            pageUrl.searchParams.get("CFG").orEmpty(),
            pageUrl.searchParams.get("SESSIONS").orEmpty(),
            pageUrl.searchParams.get("NOTIFY").orEmpty(),
        )
    }

    actual suspend fun log(message: String) {
        console.log(message)
    }

    actual suspend fun logError(message: String, ex: Exception?) {
        console.error(message, ex)
    }

    actual suspend fun loadFixtureConfiguration() = Unit
}
