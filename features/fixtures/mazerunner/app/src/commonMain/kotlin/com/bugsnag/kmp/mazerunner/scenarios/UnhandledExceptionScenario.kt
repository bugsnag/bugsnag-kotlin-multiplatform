package com.bugsnag.kmp.mazerunner.scenarios

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.EnabledErrorTypes
import com.bugsnag.kmp.User
import com.bugsnag.kmp.mazerunner.Scenario

object UnhandledExceptionScenario : Scenario("UnhandledExceptionScenario") {
    override suspend fun startBugsnag(config: String) {
        startBugsnag {
            setEnabledErrorTypes(EnabledErrorTypes(iosOoms = false))

            apiKey = "f005ba11abacaf005ba11abacaf005ba"
            appVersion = "test_app_version"
            context = "test_context_1"
            launchDurationMillis = 1234
            user = User(id = "123", name = "Adam", email = "user@example.com")

            addMetadata("custom_data_1", "data", "hello")
            addMetadata("custom_data_1", "key", "something_else")
            clearMetadata("custom_data_1", "data")

            addFeatureFlag("feature_flag_1")
            clearFeatureFlags()
            addFeatureFlag("feature_flag_2", "2")
            addFeatureFlag("feature_flag_3")
        }
    }

    override suspend fun runScenario(config: String) {
        startBugsnag(config)

        Bugsnag.addMetadata("custom_data_2", "after_start_2", "hello")
        Bugsnag.addMetadata(
            "custom_data_3",
            "after_start_3",
            "divert all available power to the crash reporter",
        )
        Bugsnag.clearMetadata("custom_data_3")

        Bugsnag.addFeatureFlag("feature_flag_4")
        Bugsnag.addFeatureFlag("feature_flag_5")
        Bugsnag.clearFeatureFlag("feature_flag_5")
        Bugsnag.addFeatureFlag("feature_flag_6", "6")

        Bugsnag.user = User(id = "456", name = "Amy", email = "user_amy@example.com")
        Bugsnag.context = "test_context_2"

        throw RuntimeException("this is a large crisis")
    }
}
