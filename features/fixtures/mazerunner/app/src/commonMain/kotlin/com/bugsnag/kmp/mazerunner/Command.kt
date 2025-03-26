package com.bugsnag.kmp.mazerunner

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
@OptIn(ExperimentalSerializationApi::class)
data class Command(
    @SerialName("action") val action: String,
    @SerialName("scenario_name") val scenarioName: String = "",
    @SerialName("scenario_mode") val scenarioConfig: String = "",
    @SerialName("sessions_endpoint") val sessionsEndpoint: String = "",
    @SerialName("notify_endpoint") val notifyEndpoint: String = "",
    @SerialName("message") val message: String? = null,
) {
    companion object {
        const val ACTION_NOOP = "noop"
        const val ACTION_RUN_SCENARIO = "run_scenario"
        const val ACTION_START_BUGSNAG = "start_bugsnag"
    }
}
