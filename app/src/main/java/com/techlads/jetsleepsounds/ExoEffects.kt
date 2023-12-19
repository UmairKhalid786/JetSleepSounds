package com.techlads.jetsleepsounds

import android.media.audiofx.PresetReverb
import android.util.Log
import androidx.media3.common.AuxEffectInfo
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.techlads.jetsleepsounds.player.Player

class ExoEffects(val player: ExoPlayer) : Player.Effects {
    private val reverb by lazy {
        PresetReverb(1, player.audioSessionId).apply {
            enabled = true
            preset = PresetReverb.PRESET_LARGEHALL
        }
    }

    override fun setEqualizer() {
        TODO("Not yet implemented")
    }

    override fun removeEqualizer() {
        TODO("Not yet implemented")
    }

    @UnstableApi override fun setReverb() {
        player.setAuxEffectInfo(AuxEffectInfo(reverb.id,1f))
    }

    override fun removeReverb() {
        reverb.enabled = false
    }

    override fun isReverb() = reverb.enabled

    override fun setBassBoost() {
        TODO("Not yet implemented")
    }

    override fun setVirtualizer() {
        TODO("Not yet implemented")
    }

    override fun setPitch(pitch: Float) {
        if (pitch > 2f || pitch < 0.1f) {
            Log.e("ExoPlayerImp", "Pitch should be between 0.1f to 2f")
            return
        }
        player.playbackParameters = PlaybackParameters(player.playbackParameters.speed, pitch)
    }

    override fun getPitch() = player.playbackParameters.pitch

    override fun setSpeed(speed: Float) {
        if (speed > 2f || speed < 0.1f) {
            Log.e("ExoPlayerImp", "Speed should be between 0.1f to 2f")
            return
        }
        player.setPlaybackSpeed(speed)
    }

    override fun getSpeed() = player.playbackParameters.speed

    override fun setVolume(volume: Float) {
        player.volume = volume
    }

    override fun getVolume() = player.volume

    override fun setOnLoop(enabled: Boolean) {
        player.repeatMode = if (enabled) ExoPlayer.REPEAT_MODE_ONE else ExoPlayer.REPEAT_MODE_OFF
    }

    override fun isOnLoop() = player.repeatMode == ExoPlayer.REPEAT_MODE_ONE

}