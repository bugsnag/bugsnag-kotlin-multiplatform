package com.bugsnag.kmp.mazerunner.scenarios

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.mazerunner.Scenario

object UnhandledExceptionScenario : Scenario("UnhandledExceptionScenario") {
    override suspend fun runScenario(config: String) {
        startBugsnag {
            addMetadata("custom_data_1", "data", "hello")
            addMetadata("custom_data_1", "key", "something_else")
            clearMetadata("custom_data1", "data")

            addFeatureFlag("feature_flag_1")
            addFeatureFlag("feature_flag_2")
            clearFeatureFlag("feature_flag_2")

            addFeatureFlag("feature_flag_3")
            clearFeatureFlags()
        }
        Bugsnag.addMetadata("custom_data_2", "after_start_2", "hello")
        Bugsnag.addMetadata(
            "custom_data_3",
            "after_start_3",
            "divert all available power to the crash reporter",
        )
        Bugsnag.addFeatureFlag("feature_flag_4")
        Bugsnag.addFeatureFlag("feature_flag_5")
        Bugsnag.clearFeatureFlag("feature_flag_5")

        Bugsnag.addFeatureFlag("feature_flag_6")
        Bugsnag.clearFeatureFlags()

        throw RuntimeException("this is a large crisis")
    }
}
