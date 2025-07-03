package com.bugsnag.kmp.util

private external fun delete(x: dynamic): Boolean
private val Object = js("Object")

private fun keysOf(js: dynamic): Array<String> {
    return Object.keys(js) as Array<String>
}

internal class DynamicMap<V>(private val js: dynamic) : AbstractMutableMap<String, V>() {
    private var entriesView: MutableSet<MutableMap.MutableEntry<String, V>>? = null

    override val entries: MutableSet<MutableMap.MutableEntry<String, V>>
        get() {
            if (entriesView == null) {
                entriesView = DynamicMapEntries(js)
            }
            return entriesView!!
        }

    override fun containsKey(key: String): Boolean {
        return js[key] !== undefined
    }

    override fun get(key: String): V? {
        val value = js[key]
        if (value === undefined) {
            return null
        }
        return value as? V
    }

    override fun put(key: String, value: V): V? {
        val existingValue = js[key]
        js[key] = value

        if (existingValue === undefined) {
            return null
        }

        return existingValue as? V
    }

    override fun remove(key: String): V? {
        val existingValue = js[key]
        delete(js[key])

        if (existingValue !== undefined) {
            return existingValue as? V
        }

        return null
    }
}

private class DynamicMapEntries<V>(private val js: dynamic) :
    AbstractMutableSet<MutableMap.MutableEntry<String, V>>() {

    override val size: Int
        get() = keysOf(js).size

    override fun contains(element: MutableMap.MutableEntry<String, V>): Boolean {
        return js[element.key] != undefined
    }

    override fun add(element: MutableMap.MutableEntry<String, V>): Boolean {
        js[element.key] = element.value
        return true
    }

    override fun clear() {
        for (key in keysOf(js)) {
            delete(js[key])
        }
    }

    override fun iterator(): MutableIterator<MutableMap.MutableEntry<String, V>> {
        return object : DynamicMutableIterator<MutableMap.MutableEntry<String, V>>(js) {
            override fun map(key: String): MutableMap.MutableEntry<String, V> {
                return object : MutableMap.MutableEntry<String, V> {
                    override var key: String = key
                    override var value: V = js[key] as V

                    override fun setValue(newValue: V): V {
                        val oldValue = value
                        value = newValue
                        js[key] = newValue
                        return oldValue
                    }

                    override fun toString(): String {
                        return "$key=$value"
                    }
                }
            }
        }
    }
}

private abstract class DynamicMutableIterator<V>(private val js: dynamic) : MutableIterator<V> {
    private val keys = keysOf(js)
    private var index = 0

    protected abstract fun map(key: String): V

    override fun hasNext(): Boolean {
        return index < keys.size
    }

    override fun next(): V {
        if (!hasNext()) throw NoSuchElementException("No more elements in the iterator")
        val key = keys[index++]
        return map(key)
    }

    override fun remove() {
        if (index == 0) throw IllegalStateException("next() has not been called yet")
        delete(js[keys[index - 1]])
    }
}
