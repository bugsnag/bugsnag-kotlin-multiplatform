package com.example.bugsnag.kmp.sharedui

expect fun getPlatformName(): String

expect fun getPlatformBugsnagKey(): String

expect fun throwNative()

expect fun throwNotResponding()
