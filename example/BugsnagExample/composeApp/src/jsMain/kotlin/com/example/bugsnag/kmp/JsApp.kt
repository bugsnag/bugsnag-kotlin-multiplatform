package com.example.bugsnag.kmp

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
    startBugsnag(Configuration(BUGSNAG_API_KEY))
    renderComposable(rootId) {
        Div {
            H1 {
                Text("Bugsnag KMP - Example app")
            }
            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex); flexDirection(FlexDirection.Column); width(20.percent);
                    }
                },
            ) {
                TextButton(
                    text = "Trigger A Fatal Crash",
                    onClick = { Bugsnag.notify(Exception("Fatal Crash")) },
                )
                TextButton(
                    text = "Attach Custom Breadcrumbs",
                    onClick = {
                        Bugsnag.leaveBreadcrumb("WebAuthFailure")
                        Bugsnag.notify(RuntimeException("Error Report with Breadcrumbs"))
                    },
                )
            }
        }
    }
}
