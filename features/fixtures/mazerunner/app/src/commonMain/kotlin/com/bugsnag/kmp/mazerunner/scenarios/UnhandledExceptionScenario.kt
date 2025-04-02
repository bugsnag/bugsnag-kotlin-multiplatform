package com.bugsnag.kmp.mazerunner.scenarios

import com.bugsnag.kmp.mazerunner.Scenario

object UnhandledExceptionScenario : Scenario("UnhandledExceptionScenario") {
    override suspend fun runScenario(config: String) {
        startBugsnag(config)
        throw RuntimeException("this is a large crisis")
    }
}
