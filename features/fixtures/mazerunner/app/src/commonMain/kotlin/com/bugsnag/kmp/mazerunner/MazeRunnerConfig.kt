package com.bugsnag.kmp.mazerunner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MazeRunnerConfig(
    @SerialName("maze_address") val endpoint: String,
)

val MazeRunnerConfig.endpointURL: String
    get() = "http://$endpoint"
