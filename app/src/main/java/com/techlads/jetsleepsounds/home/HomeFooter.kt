@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.Text
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techlads.jetsleepsounds.R
import com.techlads.jetsleepsounds.mixer.Mixer
import com.techlads.jetsleepsounds.utils.pointMeAfter
import kotlin.time.Duration.Companion.seconds


@Composable
fun HomeFooter(onMixerClick: () -> Unit = {}) {
    Row(
        Modifier
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val mixer = remember { Mixer.players }

        NowPlayingAnimation(
            modifier = Modifier
                .width(80.dp)
                .height(50.dp),
            isPlaying = mixer.size > 0
        )

        ActivePlayList(modifier = Modifier.weight(1f))

        AnimatedContent(
            targetState = if (mixer.size > 0) "Mixer is playing tracks ${mixer.size}" else "Click cards to play",
            label = ""
        ) {
            Text(text = it)
        }

        MixerFooter(onMixerClick = onMixerClick, modifier = Modifier
            .size(50.dp)
            .pointMeAfter(time = 5.seconds, thenCompleteTour = true)
        )

    }
}

@Composable
fun MixerFooter(onMixerClick: () -> Unit, modifier: Modifier) {
    IconButton(onClick = onMixerClick, modifier = modifier) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = R.drawable.sound_setting),
            contentDescription = "Mixer"
        )
    }
}

@Composable
fun NowPlayingAnimation(modifier: Modifier, isPlaying: Boolean) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            if (isPlaying) {
                R.raw.music
            } else {
                R.raw.sleeping_cat
            }
        )
    )

    LottieAnimation(
        composition,
        modifier = modifier,
        iterations = LottieConstants.IterateForever,
    )
}
