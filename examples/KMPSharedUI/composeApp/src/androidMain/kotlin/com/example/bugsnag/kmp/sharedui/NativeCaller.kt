package com.example.bugsnag.kmp.sharedui

class NativeCaller {
    init {
        System.loadLibrary("project")
    }

    external fun throwNative();

    external fun throwNativeANR();
}
