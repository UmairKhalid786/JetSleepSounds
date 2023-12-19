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
    fun speed(): Float
    fun speed(speed: Float)
    fun pitch(): Float
    fun pitch(pitch: Float)
    fun setVolume(volume: Float)
    fun setOnLoop(enabled: Boolean)
    fun isOnLoop(): Boolean
    fun title(): String
    fun togglePlayPause()
    fun setReverb()
    fun removeReverb()
    fun isReverb() : Boolean
    val isPlaying: Boolean
    val state: SharedFlow<PlayerState>

    interface Effects {
        fun setEqualizer()
        fun removeEqualizer()
        fun setReverb()
        fun removeReverb()
        fun isReverb() : Boolean
        fun setBassBoost()
        fun setVirtualizer()
        fun setPitch(pitch: Float)
        fun getPitch(): Float
        fun setSpeed(speed: Float)
        fun getSpeed(): Float
        fun setVolume(volume: Float)
        fun getVolume(): Float
        fun setOnLoop(enabled: Boolean)
        fun isOnLoop(): Boolean
    }
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