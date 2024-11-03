package com.techlads.jetsleepsounds.mixer.ui.preference

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.ExoPlayer
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.ExoPlayerImp
import com.techlads.jetsleepsounds.components.JSSCheckbox
import com.techlads.jetsleepsounds.mixer.ui.Preference
import com.techlads.jetsleepsounds.player.Player
import com.techlads.jetsleepsounds.ui.theme.JetSleepSoundsTheme

@Composable
fun LoopPref(
    player: Player,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isOnLoop = remember { mutableStateOf(player.isOnLoop()) }

    Preference {
        Text(text = "Loop", Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
        JSSCheckbox(
            isChecked = isOnLoop.value,
            modifier = Modifier.size(20.dp),
            onCheckedChange = {
                isOnLoop.value = it
                player.setOnLoop(it)
            },
            interactionSource = interactionSource
        )
    }
}

@Preview
@Composable
private fun LoopPrefPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    JetSleepSoundsTheme {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .width(300.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            LoopPref(ExoPlayerImp(exoPlayer = ExoPlayer.Builder(context).build()))
            LoopPref(ExoPlayerImp(exoPlayer = ExoPlayer.Builder(context).build()))
        }
    }
}