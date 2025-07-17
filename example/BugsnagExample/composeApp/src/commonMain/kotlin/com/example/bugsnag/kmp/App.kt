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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bugsnag.kmp.Bugsnag
import kotlinx.coroutines.delay

@Composable
fun App() {
    val message = remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Bugsnag KMP - Example app")

            Button(
                onClick = {
                    throw RuntimeException("Unhandled Kotlin Exception")
                    message.value = "Unhandled Kotlin Exception triggered"
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Throw An Unhandled Exception")
            }

            Button(
                onClick = {
                    Bugsnag.notify(RuntimeException("Handled Kotlin Exception"))
                    message.value = "Handled Kotlin Exception reported"
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("A Handled Exception")
            }

            Button(
                onClick = {
                    Bugsnag.leaveBreadcrumb(
                        "WebAuthFailure",
                        mapOf("reason" to "incorrect password"),
                    )
                    Bugsnag.notify(RuntimeException("Error Report with Breadcrumbs"))
                    message.value = "Custom Breadcrumbs attached"
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Attach Custom Breadcrumbs")
            }

            if (message.value.isNotEmpty()) {
                Text(
                    text = message.value,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
                LaunchedEffect(message.value) {
                    delay(1500)
                    message.value = ""
                }
            }
        }
    }
}
