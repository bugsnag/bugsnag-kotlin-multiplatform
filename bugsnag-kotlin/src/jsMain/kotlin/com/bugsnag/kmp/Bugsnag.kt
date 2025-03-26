package com.bugsnag.kmp

public actual object Bugsnag {
    public actual fun start(configuration: Configuration) {
        JsBugsnag.start(configuration.native)
    }

    public actual fun addMetadata(section: String, key: String, value: Any?) {
        JsBugsnag.addMetadata(section, key, value)
    }

    public actual fun addMetadata(section: String, data: Map<String, Any>) {
        val dynamicData = data.convertToDynamic()
        JsBugsnag.addMetadata(section, dynamicData)
    }

    public actual fun startSession() {
        JsBugsnag.startSession()
    }

    public actual fun pauseSession() {
        JsBugsnag.pauseSession()
    }

    public actual fun resumeSession(): Boolean = JsBugsnag.resumeSession()

    public actual fun clearMetadata(section: String) {
        JsBugsnag.clearMetadata(section)
    }

    public actual fun clearMetadata(section: String, key: String) {
        JsBugsnag.clearMetadata(section, key)
    }

     public actual fun clearFeatureFlag(name: String) {
        JsBugsnag.clearFeatureFlag(name)
    }

    public actual fun clearFeatureFlags() {
        JsBugsnag.clearFeatureFlags()
    }
}
