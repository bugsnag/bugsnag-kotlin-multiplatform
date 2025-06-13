package org.example.project

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration

fun startBugsnag(configuration: Configuration) {
    configuration.addMetadata("App", "multiplatform", true)
    configuration.addMetadata("App", "platform", getPlatformName())
    Bugsnag.start(configuration)
}