package org.example.project

class NativeCaller {
    init {
        System.loadLibrary("project")
    }

    external fun throwNative();

    external fun throwNativeANR();
}