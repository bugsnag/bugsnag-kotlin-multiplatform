package com.bugsnag.kmp

import kotlin.test.Test
import kotlin.test.assertEquals

internal class EventTest {

    @Test
    fun eventTest() {
        val jsEvent = JsEvent("Error", "An error occurred")
        val testEvent = Event(jsEvent)

        testEvent.apiKey = API_KEY
        testEvent.context = CONTEXT
        testEvent.groupingHash = "new groupingHash"
        testEvent.severity = Severity.WARNING
        testEvent.user = User(id = USER_ID, email = USER_EMAIL, name = USER_NAME)
        testEvent.addMetadata("new_1", "scenario", "EventScenario 1")
        testEvent.addMetadata("new_2", "scenario", mapOf("test2" to "EventScenario 2"))
        testEvent.addFeatureFlag("flag", "new flag 1")
        testEvent.addFeatureFlag("flag 2", "new flag 2")
        testEvent.clearMetadata("new_1")
        testEvent.clearFeatureFlag("flag")

        assertEquals(API_KEY, jsEvent.asDynamic().apiKey)
        assertEquals(CONTEXT, jsEvent.asDynamic().context)
        assertEquals("new groupingHash", jsEvent.asDynamic().groupingHash)
        assertEquals("warning", jsEvent.asDynamic().severity)
        assertEquals(USER_ID, jsEvent.asDynamic()._user.id)
        assertEquals(USER_NAME, jsEvent.asDynamic()._user.name)
        assertEquals(USER_EMAIL, jsEvent.asDynamic()._user.email)
        assertEquals("EventScenario 2", jsEvent.asDynamic()._metadata.new_2.scenario["test2"])
        assertEquals("flag 2", jsEvent.asDynamic()._features[1].name)
        assertEquals("new flag 2", jsEvent.asDynamic()._features[1].variant)
    }

    companion object {
        const val API_KEY = "decafbad"
        const val CONTEXT = "coffee shop"
        const val USER_ID = "435897634876"
        const val USER_NAME = "Hames"
        const val USER_EMAIL = "test@example.com"
    }
}
