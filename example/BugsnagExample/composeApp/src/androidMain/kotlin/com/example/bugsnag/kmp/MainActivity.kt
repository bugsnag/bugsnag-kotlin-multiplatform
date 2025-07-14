package com.example.bugsnag.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bugsnag.kmp.BreadcrumbType
import com.bugsnag.kmp.Bugsnag
import com.bugsnag.kmp.Configuration

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = Configuration(applicationContext)
        Bugsnag.start(config)


        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Bugsnag KMP - Android example app")

        Button(
            onClick = {
                Bugsnag.notify(RuntimeException("Fatal Crash"))
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Trigger A Fatal Crash")
        }

        Button(
            onClick = {
                Bugsnag.leaveBreadcrumb("LoginButtonClick")

                val metadata = mapOf(Pair("reason", "incorrect password"))
                Bugsnag.leaveBreadcrumb("WebAuthFailure", metadata, BreadcrumbType.ERROR)
                Bugsnag.notify(RuntimeException("Error Report with Breadcrumbs"))
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Attach Custom Breadcrumbs")
        }
    }
}
