#include <jni.h>
#include <signal.h>
#include <stdlib.h>
#include <string.h>
#include <linux/resource.h>
#include <sys/resource.h>
#include <android/log.h>

JNIEXPORT void JNICALL
Java_com_example_bugsnag_kmp_sharedui_NativeCaller_throwNative(JNIEnv *env, jobject thiz) {
    abort();
}

volatile unsigned uint_f2wk124_dont_optimize_me_bro;

_Noreturn void trigger_anr() {
    for (unsigned i = 0;; i++) {
        uint_f2wk124_dont_optimize_me_bro = i;
    }
}

JNIEXPORT void JNICALL
Java_com_example_bugsnag_kmp_sharedui_NativeCaller_throwNativeANR(JNIEnv *env, jobject thiz) {
    trigger_anr();
}