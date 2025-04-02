package com.bugsnag.kmp

internal external interface FeatureFlag {
    var name: String
    var variant: String?
}
