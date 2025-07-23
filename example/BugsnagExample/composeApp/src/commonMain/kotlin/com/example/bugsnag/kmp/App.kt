package com.example.bugsnag.kmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bugsnag.kmp.Bugsnag
import kotlinx.coroutines.delay

@Composable
fun App() {
    var message by remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement
                .spacedBy(12.dp),
        ) {
            Text("Bugsnag KMP - Example app")

            TextButton(
                text = "Throw An Unhandled Exception",
                onClick = {
                    message = "Unhandled Kotlin Exception triggered"
                    throw RuntimeException("Unhandled Kotlin Exception")
                },
            )
            TextButton(
                text = "A Handled Exception",
                onClick = {
                    Bugsnag.notify(RuntimeException("Handled Kotlin Exception"))
                    message = "Handled Kotlin Exception reported"
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
                    message = "Custom Breadcrumbs attached"
                },
            )

            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red,
                )
                LaunchedEffect(message) {
                    delay(1500)
                    message = ""
                }
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
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text)
    }
}
