package com.bugsnag.kmp.mazerunner

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    App()

    LaunchedEffect(Unit) {
        MazeRunner.start()
    }
}
