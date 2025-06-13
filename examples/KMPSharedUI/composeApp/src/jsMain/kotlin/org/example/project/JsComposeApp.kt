package org.example.project

import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
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
                Text("Your current UA: ${getPlatform().name}")
            }
            Div(attrs = {
                style {
                    display(DisplayStyle.Flex); flexDirection(FlexDirection.Column); width(20.percent);
                }
            }) {
                Button(attrs = {
                    onClick { throw Error("JS Error") }
                }) { Text("Throw error") }
                Button(attrs = { onClick { throwNative() } }) {
                    Text(
                        "Throw native error"
                    )
                }
                Button(attrs = { onClick { throwNativeAsync() } }) { Text("Throw native async error") }
                Button(attrs = { onClick { throwNativeAsyncHandled(Bugsnag) } }) { Text("Throw native async error handled") }
                Button(attrs = {
                    onClick { Bugsnag.notify(Exception("Bugsnag notify on ${getPlatformName()}")) }
                }) { Text("Notify error") }
                Button(attrs = {
                    onClick { Bugsnag.leaveBreadcrumb("Button clicked on ${getPlatformName()}") }
                }) { Text("Leave breadcrumb") }
                Button(attrs = {
                    onClick { Bugsnag.addMetadata("Kotlin", "multi", "platform") }
                }) { Text("Add metadata") }
            }
        }
    }
}