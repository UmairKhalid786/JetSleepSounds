@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.mixer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Switch
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.mixer.ui.preference.LoopPref
import com.techlads.jetsleepsounds.mixer.ui.preference.VolumePref
import com.techlads.jetsleepsounds.player.Player

@Composable
fun PlayerPreferences(player: Player, hideDetails: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = player.title(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Column (verticalArrangement = Arrangement.spacedBy(8.dp)) {
            VolumePref(player)
            LoopPref(player)
            SpeedPref(player)
            PitchPref(player)
            ReverbPref(player)
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(onClick = { hideDetails() }) {
                Text(text = "Done")
            }
        }
    }
}

@Composable
fun PitchPref(player: Player) {
    val pitch = remember { mutableFloatStateOf(player.pitch()) }

    Preference {

        Text(text = "Pitch" , Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)

        IconButton(onClick = {
            player.pitch(pitch.floatValue - 0.1f)
            pitch.floatValue = player.pitch()
        }, shape = ButtonDefaults.shape(RoundedCornerShape(4.dp))) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Remove",
            )
        }

        Text(text = "${(pitch.floatValue * 100f).toInt()}", style = MaterialTheme.typography.labelMedium)

        IconButton(onClick = {
            player.pitch(pitch.floatValue + 0.1f)
            pitch.floatValue = player.pitch()
        }, shape = ButtonDefaults.shape(RoundedCornerShape(4.dp))) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "PitchUp",
            )
        }
    }
}

@Composable
fun ReverbPref(player: Player) {
    val reverb = remember { mutableStateOf(player.isReverb()) }
    Preference {
        Text(text = "Reverb" , Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
        Switch(checked = reverb.value, onCheckedChange = {
            if (it) player.setReverb() else player.removeReverb()
            reverb.value = player.isReverb()
        })
    }
}

@Composable
fun SpeedPref(player: Player) {
    val speed = remember { mutableFloatStateOf(player.speed()) }

    Preference {

        Text(text = "Speed" , Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)

        IconButton(onClick = {
            player.speed(speed.floatValue - 0.1f)
            speed.floatValue = player.speed()
        }, shape = ButtonDefaults.shape(RoundedCornerShape(4.dp))) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Remove",
            )
        }

        Text(text = "${(speed.floatValue * 100f).toInt()}", style = MaterialTheme.typography.labelMedium)

        IconButton(onClick = {
            player.speed(speed.floatValue + 0.1f)
            speed.floatValue = player.speed()
        }, shape = ButtonDefaults.shape(RoundedCornerShape(4.dp))) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "SpeedUp",
            )
        }
    }
}
