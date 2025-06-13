package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

            Button(onClick = { throw Exception("Compose throw on ${getPlatformName()}") }) {
                Text("Throw error")
            }
            Button(onClick = { throwNative() }) {
                Text("Throw native error")
            }
            Button(onClick = { throwNotResponding() }) { Text("Trigger not responding error") }
            Button(onClick = { Bugsnag.notify(Exception("Bugsnag notify on ${getPlatformName()}")) }) {
                Text("Notify error")
            }
            Button(onClick = { Bugsnag.leaveBreadcrumb("Button clicked on ${getPlatformName()}") }) {
                Text("Leave breadcrumb")
            }
            Button(onClick = { Bugsnag.addMetadata("Kotlin", "multi", "platform") }) {
                Text("Add metadata")
            }
        }
    }
}