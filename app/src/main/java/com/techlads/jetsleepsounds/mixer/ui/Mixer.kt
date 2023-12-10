@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.mixer.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.Border
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import com.techlads.jetsleepsounds.player.Player


@Composable
fun Mixer(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest, properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true,
        )
    ) {

        Surface(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            border = Border(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.border),
                shape = MaterialTheme.shapes.medium
            )
        ) {
            val showDetails = remember { mutableStateOf(false) }
            val player = remember { mutableStateOf<Player?>(null) }

            if (showDetails.value && player.value != null) {
                PlayerPreferences(player.value!!, hideDetails = {
                    showDetails.value = false
                    player.value = null
                })
            } else {
                PlayersList(onDismissRequest = onDismissRequest, onPlayerDetails = {
                    showDetails.value = true
                    player.value = it
                })
            }
        }
    }
}
