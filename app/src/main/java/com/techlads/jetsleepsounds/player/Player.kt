package com.techlads.jetsleepsounds.player

import android.content.Context
import android.view.View
import kotlinx.coroutines.flow.SharedFlow

interface Player {
    fun load(url: String, title: String, playWhenReady: Boolean)
    fun play()
    fun pause()
    fun stop()
    fun release()
    fun volume(): Float
    fun setVolume(volume: Float)
    fun setOnLoop(enabled: Boolean)
    fun isOnLoop() : Boolean
    fun title(): String
    fun togglePlayPause()
    val isPlaying: Boolean
    val state: SharedFlow<PlayerState>
}

interface PlayerUi {
    fun build(context: Context): View
}

sealed class PlayerState {
    data object Idle : PlayerState()
    data object Playing : PlayerState()
    data object Pause : PlayerState()
    data object Buffering : PlayerState()
    data object Ended : PlayerState()
    data object Loading : PlayerState()
}