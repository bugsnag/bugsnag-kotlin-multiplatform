package com.bugsnag.kmp

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ConfigurationTest {
    @Test
    fun smokeTest() {
        val configuration = Configuration(API_KEY)
        configuration.appVersion = APP_VERSION
        configuration.autoTrackSessions = false
        configuration.launchDurationMillis = 987654321
        configuration.maxBreadcrumbs = MAX_BREADCRUMBS
        configuration.context = CONTEXT
        configuration.user = User(id = USER_ID, name = USER_NAME)
        configuration.releaseStage = "unit-testing"
        configuration.enabledReleaseStages = setOf("unit-testing", "staging")

        configuration.addMetadata(
            "section",
            mapOf(
                "integer" to 123,
                "number" to 99.876,
                "bool" to false,
                "list" to listOf("1", "2", 3, false),
                "map" to mapOf("nested" to "value"),
            ),
        )

        configuration.addMetadata("section", "additionalKey", "stringValue")

        configuration.addFeatureFlag("feature")
        configuration.addFeatureFlag("decaf")
        configuration.addFeatureFlag("roast_level", "light")
        configuration.addFeatureFlag("process", "swiss_water")
        configuration.addFeatureFlag("roast_level", "medium")
        configuration.clearFeatureFlag("feature")

        configuration.addRedactedKeys("psaaword")
        configuration.addRedactedKeys(listOf("hidden", "secret"))

        configuration.setEnabledErrorTypes(EnabledErrorTypes())
        configuration.setEndpoints(
            notify = NOTIFY_ENDPOINT,
            sessions = SESSIONS_ENDPOINT,
        )

        val json = JSON.stringify(configuration.native, null, 4)
        assertEquals(API_KEY, configuration.apiKey)
        assertEquals(EXPECTED_JSON, json)
    }

    companion object {
        const val API_KEY = "decafbad"
        const val APP_VERSION = "9.99.999"
        const val MAX_BREADCRUMBS = 99
        const val CONTEXT = "coffee shop"
        const val USER_ID = "435897634876"
        const val USER_NAME = "Hames"
        const val NOTIFY_ENDPOINT = "https://example.com/notify"
        const val SESSIONS_ENDPOINT = "https://example.com/sessions"

        val EXPECTED_JSON = """
            {
                "apiKey": "$API_KEY",
                "appVersion": "$APP_VERSION",
                "autoTrackSessions": false,
                "maxBreadcrumbs": $MAX_BREADCRUMBS,
                "context": "$CONTEXT",
                "user": {
                    "id": "$USER_ID",
                    "name": "$USER_NAME"
                },
                "releaseStage": "unit-testing",
                "enabledReleaseStages": [
                    "unit-testing",
                    "staging"
                ],
                "metadata": {
                    "section": {
                        "integer": 123,
                        "number": 99.876,
                        "bool": false,
                        "list": [
                            "1",
                            "2",
                            3,
                            false
                        ],
                        "map": {
                            "nested": "value"
                        },
                        "additionalKey": "stringValue"
                    }
                },
                "featureFlags": [
                    {
                        "name": "decaf"
                    },
                    {
                        "name": "process",
                        "variant": "swiss_water"
                    },
                    {
                        "name": "roast_level",
                        "variant": "medium"
                    }
                ],
                "redactedKeys": [
                    "psaaword",
                    "hidden",
                    "secret"
                ],
                "enabledErrorTypes": {
                    "unhandledExceptions": true,
                    "unhandledRejections": true
                },
                "endpoints": {
                    "notify": "$NOTIFY_ENDPOINT",
                    "sessions": "$SESSIONS_ENDPOINT"
                }
            }
        """.trimIndent()
    }
}
