package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.mazerunner.Platform.loadFixtureConfiguration
import com.bugsnag.kmp.mazerunner.Platform.logError
import com.bugsnag.kmp.mazerunner.Platform.nextCommand
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

object MazeRunner {
    val logChannel = Channel<String>()

    fun start() {
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch {
            startImpl()
        }
    }

    private suspend fun startImpl() {
        MazeLogger.log("loadFixtureConfiguration()")
        loadFixtureConfiguration()
        MazeLogger.log("waiting for commands...")

        while (true) {
            val command = nextCommand()
            if (command == null) {
                delay(500.milliseconds)
                continue
            }

            MazeLogger.log("received command: $command")

            when (command.action) {
                Command.ACTION_NOOP -> {
                    delay(500.milliseconds)
                    continue
                }
            }

            MazeLogger.log("configureEndpoints(\"${command.notifyEndpoint}\", \"${command.sessionsEndpoint}\")")
            Platform.configureEndpoints(command.notifyEndpoint, command.sessionsEndpoint)

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
                        MazeLogger.log("running scenario: $scenario")
                        scenario.runScenario(command.scenarioConfig)
                    }
                }

                Command.ACTION_START_BUGSNAG -> {
                    withContext(Dispatchers.Main) {
                        MazeLogger.log("starting bugsnag for: $scenario")
                        scenario.startBugsnag(command.scenarioConfig)
                    }
                }
            }
        }
    }
}
