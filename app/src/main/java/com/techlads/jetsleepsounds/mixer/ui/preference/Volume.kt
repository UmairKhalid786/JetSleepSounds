@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.mixer.ui.preference

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.mixer.ui.Preference
import com.techlads.jetsleepsounds.player.Player

@Composable
fun VolumePref(player: Player) {
    val volume = remember { mutableFloatStateOf(player.volume()) }

    Preference {

        Text(text = "Volume" , Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)

        IconButton(onClick = {
            player.setVolume(volume.floatValue - 0.1f)
            volume.floatValue = player.volume()
        }, shape = ButtonDefaults.shape(RoundedCornerShape(4.dp))) {
            Icon(
                imageVector = Icons.Default.VolumeDown,
                contentDescription = "Remove",
            )
        }

        Text(text = "${(volume.floatValue * 100f).toInt()}", style = MaterialTheme.typography.labelMedium)

        IconButton(onClick = {
            player.setVolume(volume.floatValue + 0.1f)
            volume.floatValue = player.volume()
        }, shape = ButtonDefaults.shape(RoundedCornerShape(4.dp))) {
            Icon(
                imageVector = Icons.Default.VolumeUp,
                contentDescription = "VolumeUp",
            )
        }
    }
}