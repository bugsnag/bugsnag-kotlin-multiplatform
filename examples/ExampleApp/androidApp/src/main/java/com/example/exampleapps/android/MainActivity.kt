package com.example.bugsnag.kmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bugsnag.kmp.Configuration
import com.example.ExampleApps.SharedBugsnag

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = Configuration(applicationContext)
        SharedBugsnag().startBugsnag(config)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val bugsnag = SharedBugsnag()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Bugsnag KMP - Android example app")

        Button(
            onClick = { bugsnag.manualNotify("Manual notify from Android") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Notify Bugsnag")
        }

        Button(
            onClick = { bugsnag.triggerFatalCrash("Crash from Android button") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Trigger Fatal Crash")
        }

        Button(
            onClick = { bugsnag.setMetadata("device", "sentFromAndroid") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Set Metadata")
        }

        Button(
            onClick = { bugsnag.leaveBreadcrumbs("User tapped breadcrumb button") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Leave Breadcrumb")
        }

        Button(
            onClick = {
                // Manual ANR trigger: keep platform-specific code where needed
                android.os.Handler(android.os.Looper.getMainLooper()).post {
                    Thread.sleep(10_000)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Trigger ANR")
        }
    }
}
