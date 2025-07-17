package com.example.bugsnag.kmp

import androidx.compose.runtime.Composable
import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration
import kotlinx.browser.window
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

@JsExport
@OptIn(ExperimentalJsExport::class)
fun StartJsApp(rootId: String) {
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
                    text = "Throw An Unhandled Exception",
                    onClick = {
                        throw Exception("Unhandled Kotlin Exception")
                        window.alert("Unhandled Exception sent to Bugsnag")
                    },
                )

                TextButton(
                    text = "A Handled Exception",
                    onClick = {
                        Bugsnag.notify(Exception("Handled Kotlin Exception"))
                        window.alert("Handled Exception sent to Bugsnag")
                    },
                )

                TextButton(
                    text = "Attach Custom Breadcrumbs",
                    onClick = {
                        Bugsnag.leaveBreadcrumb(
                            "WebAuthFailure",
                            mapOf("reason" to "incorrect password"),
                        )
                        Bugsnag.notify(RuntimeException("Error Report with Breadcrumbs"))
                        window.alert("Breadcrumbs sent to Bugsnag")
                    },
                )
            }
        }
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        attrs = {
            onClick { onClick() }
        },
    ) {
        Text(text)
    }
}
