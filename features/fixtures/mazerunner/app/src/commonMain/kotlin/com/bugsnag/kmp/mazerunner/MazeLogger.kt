package com.bugsnag.kmp.mazerunner

import com.bugsnag.kmp.mazerunner.MazeRunner.logChannel

object MazeLogger {
    suspend fun log(message: String) {
        logChannel.send(message)
        Platform.log(message)
    }

    suspend fun log(message: String, error: Exception) {
        logChannel.send(message)
        Platform.logError(message, error)
    }
}
