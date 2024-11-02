package com.techlads.jetsleepsounds.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.techlads.jetsleepsounds.PlayerBuilder
import com.techlads.jetsleepsounds.mixer.Mixer.addPlayer
import com.techlads.jetsleepsounds.songs.TracksFactory
import com.techlads.jetsleepsounds.utils.pointMeAfter
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun HomeBody(modifier: Modifier) {
    val scope = rememberCoroutineScope()
    val tracks = remember {
        TracksFactory.getTracks()
    }
    LazyHorizontalGrid(modifier = modifier, rows = GridCells.Fixed(3)) {

        items(tracks.size) {
            val ctx = LocalContext.current
            val track = remember {
                tracks[it]
            }
            MusicCard(track, modifier = Modifier.then(
                if (it == 0) Modifier.pointMeAfter(1.seconds) else Modifier
            ), onClick = { _ ->
                scope.launch {
                    addPlayer(PlayerBuilder.build(ctx).apply {
                        load(track.url, track.title, true)
                    })
                }
            })
        }
    }
}