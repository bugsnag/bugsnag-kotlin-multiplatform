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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bugsnag.kmp.Bugsnag

@Composable
fun App() {
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
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Throw An Unhandled Exception")
            }

            Button(
                onClick = {
                    Bugsnag.notify(RuntimeException("Handled Kotlin Exception"))
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
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Attach Custom Breadcrumbs")
            }
        }
    }
}
