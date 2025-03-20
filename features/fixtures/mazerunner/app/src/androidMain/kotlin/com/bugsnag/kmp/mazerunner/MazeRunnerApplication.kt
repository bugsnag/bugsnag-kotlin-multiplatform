package com.bugsnag.kmp.mazerunner

import android.app.Application

class MazeRunnerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Platform.application = this
    }
}
