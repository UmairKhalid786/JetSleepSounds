@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.mixer.Mixer
import kotlinx.coroutines.delay


@Composable
fun ActivePlayList(modifier: Modifier) {
    val mixer = remember { Mixer.players }
    val fromPlaying = remember { mutableStateOf("") }

    LaunchedEffect(key1 = mixer) {
        var playingIndex = 0
        while (true) {
            if (playingIndex >= mixer.size) playingIndex = 0
            fromPlaying.value = mixer.getOrNull(playingIndex)?.title() ?: ""
            delay(2000)
            playingIndex++
        }
    }

    AnimatedContent(targetState = fromPlaying.value, label = "", modifier = modifier) {
        Text(text = it)
    }
}