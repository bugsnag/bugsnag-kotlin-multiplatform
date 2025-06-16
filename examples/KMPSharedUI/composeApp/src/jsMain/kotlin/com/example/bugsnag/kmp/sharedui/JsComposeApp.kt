package com.example.bugsnag.kmp.sharedui

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

@Suppress("FunctionName")
@JsExport
@OptIn(ExperimentalJsExport::class)
fun StartComposeAppWeb(rootId: String) {
    startBugsnag(Configuration(getPlatformBugsnagKey()))
    renderComposable(rootId) {
        Div {
            H1 {
                Text("Hello from the Compose Web!\n")
            }
            Div(attrs = {
                style {
                    display(DisplayStyle.Flex); flexDirection(FlexDirection.Column); width(20.percent);
                }
            }) {
                TextButton(
                    text = "Throw error",
                    onClick = { throw Error("JS Error") }
                )
                TextButton(
                    text = "Throw native error",
                    onClick = { throwNative() }
                )
                TextButton(
                    text = "Throw native async error",
                    onClick = { throwNativeAsync() }
                )
                TextButton(
                    text = "Throw native async error handled",
                    onClick = { throwNativeAsyncHandled(Bugsnag) }
                )
                TextButton(
                    text = "Notify error",
                    onClick = { Bugsnag.notify(Exception("Bugsnag notify on ${getPlatformName()}")) }
                )
                TextButton(
                    text = "Leave breadcrumb",
                    onClick = { Bugsnag.leaveBreadcrumb("Button clicked on ${getPlatformName()}") }
                )
                TextButton(
                    text = "Add metadata",
                    onClick = { Bugsnag.addMetadata("Kotlin", "multi", "platform") }
                )
            }
        }
    }
}