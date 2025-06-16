package com.example.bugsnag.kmp.sharedui

actual fun getPlatformName(): String {
    return "JS"
}

actual fun getPlatformBugsnagKey(): String {
    return "YOUR_JS_BUGSNAG_API_KEY"
}

actual fun throwNative() {
    js("throw new Error('Native error thrown from JS platform');")
}

actual fun throwNotResponding() {
    TODO("Not possible lol")
}

fun throwNativeAsync() {
    js("new Promise(function(_, rej){rej(new Error('Native async error thrown from JS platform'))});")
}

fun throwNativeAsyncHandled(bsg: dynamic) {
    js("new Promise(function(_, rej){rej(new Error('Native async error handled on JS platform'))}).catch(bsg.notify);")
}