package com.example.bugsnag.kmp

import androidx.compose.ui.window.ComposeUIViewController
import com.bugsnag.kmp.Configuration

@Suppress("FunctionName", "unused")
fun MainViewController() = ComposeUIViewController {
    startBugsnag(Configuration(BugsnagCommons().API_KEY))
    App()
}
