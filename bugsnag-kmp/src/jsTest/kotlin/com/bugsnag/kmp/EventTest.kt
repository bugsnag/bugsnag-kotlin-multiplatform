package com.bugsnag.kmp

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class EventTest {

    @Test
    fun eventTest() {
        val jsEvent = JsEvent("Error", "An error occurred")
        val testEvent = Event(jsEvent)

        testEvent.apply {
            apiKey = API_KEY
            context = CONTEXT
            groupingHash = "new groupingHash"
            severity = Severity.WARNING
            user = User(id = USER_ID, email = USER_EMAIL, name = USER_NAME)
            addMetadata("new_1", "string", "test string")
            addMetadata("new_2", "number", 1000)
            addMetadata("new_3", "list", listOf(1, 2, 3))
            addMetadata("new_4", "map", mapOf("test2" to "EventScenario 2"))
            addMetadata("new_6", "string2", "test string2")

            addFeatureFlag("flag", "new flag 1")
            addFeatureFlag("flag 2", "new flag 2")
            clearMetadata("new_1")
            clearFeatureFlag("flag")

            device.id = DEVICE_ID
            device.locale = DEVICE_LOCALE
            device.manufacturer = DEVICE_MANUFACTURER
            device.model = DEVICE_MODEL
            device.osName = DEVICE_OS_NAME
            device.osVersion = DEVICE_OS_VERSION

            app.releaseStage = APP_RELEASE_STAGE
            app.version = APP_VERSION
            app.codeBundleId = APP_CODE_BUNDLE_ID
            app.type = APP_TYPE
            app.duration = APP_DURATION
            app.durationInForeground = APP_DURATION_IN_FOREGROUND
            app.inForeground = APP_IN_FOREGROUND

            apiKey = API_KEY
            context = CONTEXT
            groupingHash = "new groupingHash"
            severity = Severity.WARNING
            user = User(id = USER_ID, email = USER_EMAIL, name = USER_NAME)
            addMetadata("new_1", "string", "test string")
            addMetadata("new_2", "number", 1000)
            addMetadata("new_3", "list", listOf(1, 2, 3))
            addMetadata("new_4", "map", mapOf("test2" to "EventScenario 2"))
            addMetadata("new_6", "string2", "test string2")

            addFeatureFlag("flag", "new flag 1")
            addFeatureFlag("flag 2", "new flag 2")
            clearMetadata("new_1")
            clearFeatureFlag("flag")
        }

        assertEquals(API_KEY, jsEvent.asDynamic().apiKey)
        assertEquals(CONTEXT, jsEvent.asDynamic().context)
        assertEquals("new groupingHash", jsEvent.asDynamic().groupingHash)
        assertEquals("warning", jsEvent.asDynamic().severity)
        assertEquals(USER_ID, jsEvent.asDynamic()._user.id)
        assertEquals(USER_NAME, jsEvent.asDynamic()._user.name)
        assertEquals(USER_EMAIL, jsEvent.asDynamic()._user.email)
        assertNull(jsEvent.asDynamic()._metadata.new_1)
        assertEquals("test string2", jsEvent.asDynamic()._metadata.new_6.string2)
        assertEquals("EventScenario 2", jsEvent.asDynamic()._metadata.new_4.map["test2"])
        assertEquals(1000, jsEvent.asDynamic()._metadata.new_2.number)
        assertEquals(3, jsEvent.asDynamic()._metadata.new_3.list.length)
        assertEquals("flag 2", jsEvent.asDynamic()._features[1].name)
        assertEquals("new flag 2", jsEvent.asDynamic()._features[1].variant)

        assertEquals(DEVICE_ID, jsEvent.asDynamic().device.id)
        assertEquals(DEVICE_LOCALE, jsEvent.asDynamic().device.locale)
        assertEquals(DEVICE_MANUFACTURER, jsEvent.asDynamic().device.manufacturer)
        assertEquals(DEVICE_MODEL, jsEvent.asDynamic().device.model)
        assertEquals(DEVICE_OS_NAME, jsEvent.asDynamic().device.osName)
        assertEquals(DEVICE_OS_VERSION, jsEvent.asDynamic().device.osVersion)

        assertEquals(APP_RELEASE_STAGE, jsEvent.asDynamic().app.releaseStage)
        assertEquals(APP_VERSION, jsEvent.asDynamic().app.version)
        assertEquals(APP_CODE_BUNDLE_ID, jsEvent.asDynamic().app.codeBundleId)
        assertEquals(APP_TYPE, jsEvent.asDynamic().app.type)
        assertEquals(APP_DURATION, jsEvent.asDynamic().app.duration)
        assertEquals(APP_DURATION_IN_FOREGROUND, jsEvent.asDynamic().app.durationInForeground)
        assertEquals(APP_IN_FOREGROUND, jsEvent.asDynamic().app.inForeground)
    }

    companion object {
        const val API_KEY = "decafbad"
        const val CONTEXT = "coffee shop"
        const val USER_ID = "435897634876"
        const val USER_NAME = "Hames"
        const val USER_EMAIL = "test@example.com"

        const val DEVICE_ID = "device-id"
        const val DEVICE_LOCALE = "en-US"
        const val DEVICE_MANUFACTURER = "Bugsnagger"
        const val DEVICE_MODEL = "Bugsnag Pro Max"
        const val DEVICE_OS_NAME = "JavaScript"
        const val DEVICE_OS_VERSION = "1.0.0"

        const val APP_CODE_BUNDLE_ID = "bundle-id"
        const val APP_DURATION = 1000
        const val APP_DURATION_IN_FOREGROUND = 500
        const val APP_IN_FOREGROUND = true
        const val APP_RELEASE_STAGE = "test"
        const val APP_TYPE = "web"
        const val APP_VERSION = "1.0.0"
    }
}
