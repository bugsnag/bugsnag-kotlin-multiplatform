package com.bugsnag.kmp.mazerunner.scenarios

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.EnabledErrorTypes
import com.bugsnag.kmp.User
import com.bugsnag.kmp.mazerunner.Scenario

object NotifyScenario : Scenario("NotifyScenario") {
    override suspend fun runScenario(config: String) {
        startBugsnag {
            setEnabledErrorTypes(EnabledErrorTypes(iosOoms = false))

            addMetadata("test", "scenario", "NotifyScenario")
            addMetadata("test", "badValue", "I am a bad value")
            clearMetadata("test", "badValue")

            addMetadata("test", "config", "configuration")

            addFeatureFlag("flag1", "featureFlag")
            addFeatureFlag("flag2", "featureFlag2")
            clearFeatureFlag("flag1")

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

        Bugsnag.notify(Exception("I expected this"))
    }
}
