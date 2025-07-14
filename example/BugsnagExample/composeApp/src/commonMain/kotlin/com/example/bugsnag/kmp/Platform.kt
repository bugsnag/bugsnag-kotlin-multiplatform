package com.example.bugsnag.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getPlatformBugsnagKey(): String
