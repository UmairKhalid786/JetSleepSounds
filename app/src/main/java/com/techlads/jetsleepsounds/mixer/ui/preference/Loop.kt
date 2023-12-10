@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.mixer.ui.preference

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Checkbox
import androidx.tv.material3.CheckboxDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.mixer.ui.Preference
import com.techlads.jetsleepsounds.player.Player

@Composable
fun LoopPref(player: Player) {
    val isOnLoop = remember { mutableStateOf(player.isOnLoop()) }
    Preference {
        Text(text = "Loop" , Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
        Checkbox(
            checked = isOnLoop.value,
            modifier = Modifier.size(20.dp),
            colors =  CheckboxDefaults.colors(),
            onCheckedChange = {
                isOnLoop.value = it
                player.setOnLoop(it)
            }
        )
    }
}