package com.bugsnag.kmp.mazerunner.scenarios

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Severity
import com.bugsnag.kmp.User
import com.bugsnag.kmp.mazerunner.Scenario

object EventScenario : Scenario("EventScenario") {
    override suspend fun runScenario(config: String) {
        startBugsnag {
            addMetadata("test", "scenario", "EventScenario")
            addMetadata("test", "badValue", "I am a bad value")

            addFeatureFlag("flag1", "featureFlag")
            addFeatureFlag("flag2", "featureFlag2")

            user = User(id = "xyz321", name = "User McUserface", email = "noreply@example.com")
            releaseStage = "notify"
            context = "startup context"
        }

        Bugsnag.context = "running context"

        Bugsnag.addMetadata(
            "test_data",
            mapOf(
                "string" to "a string",
                "number" to 123,
                "boolean" to true,
                "array" to listOf("a", "b", "c"),
                "map" to mapOf("a" to "b", "c" to "d"),
            ),
        )

        Bugsnag.notify(Exception("I expected this")) { event ->
            event.clearFeatureFlags()
            event.clearMetadata("test_data")
            event.apiKey = "99999999999999909999999999999999"
            event.context = "new context"
            event.groupingHash = "new groupingHash"
            event.severity = Severity.INFO
            event.user = User(id = "test123", name = "Jonny User", email = "test@example.com")
            event.addMetadata("new_1", "scenario", "EventScenario 1")
            event.addMetadata("new_2", "scenario", "EventScenario 2")

            event.addFeatureFlag("flag", "new flag 1")
            event.addFeatureFlag("flag 2", "new flag 2")

            event.clearMetadata("new_1")
            event.clearFeatureFlag("flag")
            true
        }
    }
}
