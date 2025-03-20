package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.mazerunner.Platform.loadFixtureConfiguration
import com.bugsnag.kmp.mazerunner.Platform.logError
import com.bugsnag.kmp.mazerunner.Platform.nextCommand
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

object MazeRunner {
    fun start() {
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch {
            startImpl()
        }
    }

    private suspend fun startImpl() {
        loadFixtureConfiguration()

        while (true) {
            val command = nextCommand()
            if (command == null) {
                delay(500.milliseconds)
                continue
            }

            val scenario = Scenario[command.scenarioName]

            if (scenario == null) {
                logError(
                    "scenario not found '$command'",
                    NullPointerException(command.scenarioName),
                )

                continue
            }

            when (command.action) {
                Command.ACTION_RUN_SCENARIO -> {
                    withContext(Dispatchers.Main) {
                        scenario.runScenario(command.scenarioConfig)
                    }
                }

                Command.ACTION_START_BUGSNAG -> {
                    withContext(Dispatchers.Main) {
                        scenario.startBugsnag(command.scenarioConfig)
                    }
                }
            }
        }
    }
}
