package com.example.bugsnag.kmp.sharedui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bugsnag.kmp.Bugsnag
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TextButton(
                text = "Throw error",
                onClick = { throw Exception("Compose throw on ${getPlatformName()}") }
            )
            TextButton(
                text = "Throw native error",
                onClick = { throwNative() }
            )
            TextButton(
                text = "Trigger not responding error",
                onClick = { throwNotResponding() }
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
