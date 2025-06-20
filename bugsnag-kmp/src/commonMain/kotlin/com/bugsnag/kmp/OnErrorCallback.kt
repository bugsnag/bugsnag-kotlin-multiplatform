package com.bugsnag.kmp

public fun interface OnErrorCallback {
    public fun onError(event: Event): Boolean
}
