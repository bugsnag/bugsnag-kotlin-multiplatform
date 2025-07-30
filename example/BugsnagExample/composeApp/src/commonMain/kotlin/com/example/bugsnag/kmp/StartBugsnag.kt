package com.example.bugsnag.kmp

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration

fun startBugsnag(configuration: Configuration) {
    Bugsnag.start(configuration)
}
