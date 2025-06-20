package com.example.bugsnag.kmp.sharedui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bugsnag.kmp.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        startBugsnag(Configuration(this, getPlatformBugsnagKey()))

        setContent {
            App()
        }
    }
}
