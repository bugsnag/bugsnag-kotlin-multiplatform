package com.example.ExampleApps

class JSPlatform : Platform {
    override val name: String = "JavaScript"
}

actual fun getPlatform(): Platform = JSPlatform()
