package com.bugsnag.kmp.mazerunner

import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

@JsExport
@OptIn(ExperimentalJsExport::class)
fun StartMazerunner(rootId: String) {
    renderComposable(rootId) {
        Div {
            H1 { Text("MazeRunner Test Fixture (KMP)") }
        }
    }

    MazeRunner.start()
}
