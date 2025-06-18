package com.bugsnag.kmp.mazerunner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    val log = remember { mutableStateListOf<String>() }

    LaunchedEffect(Unit) {
        for (message in MazeRunner.logChannel) {
            log.add(message)
        }
    }

    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("MazeRunner Test Fixture (KMP)")

            Spacer(Modifier.height(16.dp))

            log.forEach { log ->
                Text(
                    log,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.align(Alignment.Start) then
                        Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
