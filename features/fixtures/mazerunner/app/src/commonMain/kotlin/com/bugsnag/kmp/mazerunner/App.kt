package com.bugsnag.kmp.mazerunner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.consumeAsFlow

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

            log.forEach { log ->
                Text(
                    log,
                    Modifier.align(Alignment.Start) then
                            Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
