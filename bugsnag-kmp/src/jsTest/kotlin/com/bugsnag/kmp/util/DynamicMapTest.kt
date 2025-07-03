package com.bugsnag.kmp.util

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DynamicMapTest {
    @Test
    fun testBasicFunctionality() {
        val js = js("{}")

        val map = DynamicMap<String?>(js)
        assertTrue(map.isEmpty())

        map["key"] = "value"
        map["nullValue"] = null
        map["key2"] = "value2"

        assertEquals(3, map.size)
        assertEquals("value", map["key"])
        assertEquals("value2", map["key2"])

        assertNull(map["nullValue"])
        assertTrue(map.containsKey("nullValue"))

        assertEquals(
            """{"key":"value","nullValue":null,"key2":"value2"}""",
            JSON.stringify(js),
        )

        map.remove("nullValue")
        assertEquals(2, map.size)
        assertNull(map["nullValue"])
        assertFalse(map.containsKey("nullValue"))

        assertEquals(
            """{"key":"value","key2":"value2"}""",
            JSON.stringify(js),
        )
    }

    @Test
    fun testKeysView() {
        val js = js("""{"key1":"value1","key2":"value2"}""")
        val map = DynamicMap<String>(js)

        val keys = map.keys
        assertEquals(2, keys.size)
        assertTrue(keys.contains("key1"))
        assertTrue(keys.contains("key2"))

        val keyArray = keys.toTypedArray()
        assertContentEquals(arrayOf("key1", "key2"), keyArray)

        js.newKey = "newValue"
        assertEquals(3, keys.size)
        assertTrue(keys.contains("newKey"))
    }

    @Test
    fun testValuesView() {
        val js = js("""{"key1":"value1","key2":"value2"}""")
        val map = DynamicMap<String>(js)
        val values = map.values

        val valueArray = values.toTypedArray()
        assertContentEquals(arrayOf("value1", "value2"), valueArray)

        js.newKey = "newValue"
        assertEquals(3, values.size)
        assertTrue(values.contains("newValue"))
    }
}
