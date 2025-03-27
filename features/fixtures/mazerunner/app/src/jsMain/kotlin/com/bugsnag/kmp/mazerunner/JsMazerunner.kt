package com.bugsnag.kmp.mazerunner

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Pre
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

@JsExport
@OptIn(ExperimentalJsExport::class)
fun StartMazerunner(rootId: String) {
    renderComposable(rootId) {
        val log = remember { mutableStateListOf<String>() }

        LaunchedEffect(Unit) {
            for (message in MazeRunner.logChannel) {
                log.add(message)
            }
        }

        Div {
            H1 { Text("MazeRunner Test Fixture (KMP)") }

            Pre {
                Text(log.joinToString("\n"))
            }

            Scenario.forEach { scenario: Scenario ->
                Button(attrs = { onClick { MazeRunner.runScenario(scenario) } }) {
                    Text(scenario.name)
                }
            }
        }
    }

    MazeRunner.start()
}
