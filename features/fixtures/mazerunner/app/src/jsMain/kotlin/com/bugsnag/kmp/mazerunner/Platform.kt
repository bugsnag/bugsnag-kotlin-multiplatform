package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.Configuration
import kotlinx.browser.window
import org.w3c.dom.url.URL

actual object Platform {
    private var notifyEndpoint: String? = null
    private var sessionEndpoint: String? = null

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
            requireNotNull(pageUrl.searchParams.get("scenarioName")) { "missing 'scenarioName' query parameter" },
            pageUrl.searchParams.get("scenarioConfig").orEmpty(),
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
