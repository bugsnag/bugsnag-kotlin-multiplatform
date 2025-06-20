package com.example.ExampleApps

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration

actual class SharedBugsnag {
    actual fun startBugsnag(configuration: Configuration) {
        Bugsnag.start(configuration)
    }

    actual fun manualNotify(message: String) {
        try {
            throw IllegalStateException(message)
        } catch (e: Exception) {
            Bugsnag.notify(e)
        }
    }

    actual fun triggerFatalCrash(message: String) {
        throw RuntimeException(message)
    }

    actual fun setMetadata(section: String, key: String) {
        Bugsnag.addMetadata(section, key, true)
    }

    actual fun leaveBreadcrumbs(message: String) {
        Bugsnag.leaveBreadcrumb(message)
    }
}