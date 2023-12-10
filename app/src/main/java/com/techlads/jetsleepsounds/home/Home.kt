package com.techlads.jetsleepsounds.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.IntState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.skydoves.cloudy.Cloudy
import com.techlads.jetsleepsounds.R
import com.techlads.jetsleepsounds.mixer.ui.Mixer
import com.techlads.jetsleepsounds.utils.pointerOverlay
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

val images = listOf(
    R.drawable.b,
    R.drawable.c,
    R.drawable.e,
    R.drawable.f,
    R.drawable.h,
    R.drawable.j,
    R.drawable.k,
)

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
private fun HomeContent() {

    val isMixerVisible = remember { mutableStateOf(false) }

    Box(modifier = Modifier.pointerOverlay(Color.Black.copy(alpha = 0.9f))) {
        BackgroundImage()
        Column {
            val showHeaderAndFooter = remember {
                mutableStateOf(false)
            }
            AnimatedVisibility(
                visible = showHeaderAndFooter.value, enter = slideInVertically(
                    animationSpec = tween(durationMillis = 1000),
                    initialOffsetY = {
                        -it
                    },
                )
            ) {
                HomeHeader()
            }
            HomeBody(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = showHeaderAndFooter.value, enter = slideInVertically(
                    animationSpec = tween(durationMillis = 1000),
                    initialOffsetY = { it },
                )
            ) {
                HomeFooter { isMixerVisible.value = true }
            }

            LaunchedEffect(key1 = Unit) {
                delay(100)
                showHeaderAndFooter.value = true
            }
        }

        if (isMixerVisible.value) {
            Cloudy(modifier = Modifier.fillMaxSize(), radius = 20) {
                Mixer {
                    isMixerVisible.value = false
                }
            }
        }
    }
}

@Composable
fun rememberBackgroundImageState(): IntState {
    val imageState = remember { mutableIntStateOf(images.random()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(30.seconds)
            imageState.intValue = images.random()
        }
    }

    return imageState
}
