@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.R
import com.techlads.jetsleepsounds.songs.Track
import com.techlads.jetsleepsounds.utils.PositionPointer


@Composable
fun MusicCard(
    track: Track, onClick: (Track) -> Unit = {}, modifier: Modifier
) {
    CompactCard(modifier = modifier
        .padding(16.dp)
        .aspectRatio(1f),
        onClick = {
            onClick(track)
        },
        image = {
            Image(
                painter = painterResource(id = track.src),
                contentDescription = "Music",
                contentScale = Crop,
            )
        },
        glow = CardDefaults.glow(
            focusedGlow = Glow(
                MaterialTheme.colorScheme.primary,
                2.dp,
            )
        ),
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    2.dp,
                    MaterialTheme.colorScheme.primary
                ),
            )
        ),
        scale = CardDefaults.scale(
            focusedScale = 1.2f
        ),
        title = { },
        description = { Text(text = track.title, modifier = Modifier.padding(8.dp)) })
}
