package com.example.ExampleApps

import com.bugsnag.kmp.Configuration

expect class SharedBugsnag {
    fun startBugsnag(configuration: Configuration)

    fun manualNotify(message: String)

    fun triggerFatalCrash(message: String)

    fun setMetadata(section: String, key: String)
    
    fun leaveBreadcrumbs(message: String)
}
