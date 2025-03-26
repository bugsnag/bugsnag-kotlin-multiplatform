package com.bugsnag.kmp.mazerunner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Command(
    @SerialName("action") val action: String,
    @SerialName("scenario_name") val scenarioName: String = "",
    @SerialName("scenario_config") val scenarioConfig: String = "",
    @SerialName("message") val message: String? = null,
) {
    companion object {
        const val ACTION_NOOP = "noop"
        const val ACTION_RUN_SCENARIO = "runScenario"
        const val ACTION_START_BUGSNAG = "startBugsnag"
    }
}
