@file:OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)

package com.bugsnag.kmp

import com.bugsnag.cocoa.__bsg_kotlinCrashed
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import platform.Foundation.NSClassFromString
import platform.Foundation.NSException
import platform.Foundation.NSNumber
import platform.Foundation.NSSelectorFromString
import platform.darwin.NSUInteger
import platform.objc.class_getInstanceMethod
import platform.objc.method_getImplementation
import platform.objc.method_setImplementation
import kotlin.concurrent.AtomicInt
import kotlin.experimental.ExperimentalNativeApi
import com.bugsnag.cocoa.Bugsnag as PlatformBugsnag

private val Throwable.name: String
    get() = this::class.qualifiedName ?: this::class.simpleName ?: "Throwable"

internal class BugsnagNSException(
    name: String,
    reason: String?,
    private val stackFrameAddresses: List<NSNumber>,
) : NSException(name, reason, null) {
    constructor(throwable: Throwable) : this(
        throwable.name,
        throwable.message,
        throwable.getStackTraceAddresses().map { address ->
            NSNumber(unsignedInteger = address.convert<NSUInteger>())
        },
    )

    override fun callStackReturnAddresses(): List<*> {
        return stackFrameAddresses
    }
}

@OptIn(BetaInteropApi::class)
private fun overrideUnhandled() {
    // This function is a work-around and will be removed in a future release, once we have a
    // mechanism to affect the delivery strategy directly
    val handledState = NSClassFromString("BugsnagHandledState") ?: return
    val originalUnhandledSelector =
        NSSelectorFromString("originalUnhandledValue")
            ?: return
    val unhandledSelector =
        NSSelectorFromString("unhandled")
            ?: return
    val originalUnhandled =
        class_getInstanceMethod(handledState, originalUnhandledSelector)
            ?: return
    val unhandled =
        class_getInstanceMethod(handledState, unhandledSelector)
            ?: return

    method_setImplementation(originalUnhandled, method_getImplementation(unhandled))
}

@OptIn(ExperimentalNativeApi::class)
internal fun installUncaughtExceptionHandler() {
    val unhandledExceptionCrashed = AtomicInt(0)
    var previousHookRef: ReportUnhandledExceptionHook? = null
    val previousHook =
        setUnhandledExceptionHook { throwable ->
            // We only handle a single Kotlin crash in order to avoid swizzling issues
            if (unhandledExceptionCrashed.compareAndSet(0, 1)) {
                __bsg_kotlinCrashed = true

                overrideUnhandled()
                PlatformBugsnag.notify(BugsnagNSException(throwable)) { event ->
                    if (event == null) return@notify true
                    event.setUnhandled(true)
                    true
                }
            }

            previousHookRef?.invoke(throwable)
        }
    previousHookRef = previousHook
}
