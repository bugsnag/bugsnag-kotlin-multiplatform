package org.example.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getPlatformName(): String

expect fun getPlatformBugsnagKey(): String

expect fun throwNative()

expect fun throwNotResponding()