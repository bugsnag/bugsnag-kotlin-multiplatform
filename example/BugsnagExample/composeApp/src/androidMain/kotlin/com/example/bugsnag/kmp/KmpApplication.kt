package com.example.bugsnag.kmp

import android.app.Application
import com.bugsnag.kmp.Configuration

class KmpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val config = Configuration(applicationContext, BUGSNAG_API_KEY)
        startBugsnag(config)
    }
}
