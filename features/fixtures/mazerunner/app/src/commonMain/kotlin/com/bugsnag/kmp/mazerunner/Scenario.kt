package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration
import com.bugsnag.kmp.mazerunner.scenarios.scenarios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext

abstract class Scenario(val name: String) : CoroutineScope by MainScope() {
    abstract suspend fun runScenario(config: String)

    /**
     * Start Bugsnag with the given scenario configuration.
     */
    open suspend fun startBugsnag(config: String) {
        startBugsnag()
    }

    protected suspend fun startBugsnag(configure: Configuration.() -> Unit = {}) =
        withContext(Dispatchers.Main) {
            val configuration = Platform.createBaseConfiguration(DEFAULT_API_KEY)
            configuration.configure()
            Bugsnag.start(configuration)
        }

    companion object {
        const val DEFAULT_API_KEY = "decafbad"

        operator fun get(name: String): Scenario? = scenarios.firstOrNull { it.name == name }
    }
}
