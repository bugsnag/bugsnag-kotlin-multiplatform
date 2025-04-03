package com.bugsnag.kmp

internal external interface JsFeatureFlag {
    var name: String
    var variant: String?
}

internal fun JsFeatureFlag(name: String, variant: String?): JsFeatureFlag {
    val flag = Any().unsafeCast<JsFeatureFlag>()
    if (variant != null) {
        flag.name = name
        flag.variant = variant
    } else {
        flag.name = name
    }
    return flag
}
