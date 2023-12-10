package com.techlads.jetsleepsounds.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.techlads.jetsleepsounds.utils.gradientOverlay


@Composable
fun BackgroundImage() {
    val currentImage = rememberBackgroundImageState()
    AsyncImage(
        model = currentImage.intValue,
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize().gradientOverlay(Color.Black.copy(alpha = 0.8f)),
        contentScale = ContentScale.Crop,
    )
}