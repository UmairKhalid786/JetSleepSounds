package com.techlads.jetsleepsounds.mixer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techlads.jetsleepsounds.R
import com.techlads.jetsleepsounds.mixer.Mixer
import com.techlads.jetsleepsounds.player.Player

@Composable
fun PlayersList(onDismissRequest: () -> Unit, onPlayerDetails: (Player) -> Unit = {}) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            text = "Mixer",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        val players = remember { Mixer.players }

        if (players.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(8.dp)
            ) {
                items(players.size) {
                    Track(players[it]) {
                        onPlayerDetails(it)
                    }
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                EmptyTracks(modifier = Modifier.size(200.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "No tracks playing, play a track from the home",
                    style = MaterialTheme.typography.bodyLarge,
                    color = LocalContentColor.current.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(onClick = { onDismissRequest() }) {
                Text(text = "Done")
            }
        }
    }
}

@Composable
fun EmptyTracks(modifier: Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.empty_box
        )
    )

    LottieAnimation(
        composition,
        modifier = modifier,
        iterations = LottieConstants.IterateForever,
    )
}