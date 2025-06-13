package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import com.bugsnag.kmp.Configuration

@Suppress("FunctionName", "unused")
fun MainViewController() = ComposeUIViewController {
    startBugsnag(Configuration(getPlatformBugsnagKey()))
    App()
}