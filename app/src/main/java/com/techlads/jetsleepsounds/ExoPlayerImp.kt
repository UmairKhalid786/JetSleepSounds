package com.techlads.jetsleepsounds

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams
import androidx.core.view.updateLayoutParams
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player.Listener
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.techlads.jetsleepsounds.player.Player
import com.techlads.jetsleepsounds.player.PlayerState
import com.techlads.jetsleepsounds.player.PlayerUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


internal class ExoPlayerImp(val exoPlayer: ExoPlayer) : Player {
    var title: String = ""

    init {
        exoPlayer.addListener(object : Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    ExoPlayer.STATE_IDLE -> _playerState.tryEmit(PlayerState.Idle)
                    ExoPlayer.STATE_BUFFERING -> _playerState.tryEmit(PlayerState.Buffering)
                    ExoPlayer.STATE_READY -> _playerState.tryEmit(if (isPlaying) PlayerState.Playing else PlayerState.Pause)
                    ExoPlayer.STATE_ENDED -> _playerState.tryEmit(PlayerState.Ended)
                }
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
                super.onPlayWhenReadyChanged(playWhenReady, reason)
                if (playWhenReady) {
                    _playerState.tryEmit(PlayerState.Playing)
                } else {
                    _playerState.tryEmit(PlayerState.Pause)
                }
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                super.onIsLoadingChanged(isLoading)
                if (isLoading) {
                    _playerState.tryEmit(PlayerState.Loading)
                }
            }
        })
    }

    val _playerState = MutableSharedFlow<PlayerState>(replay = 1)

    override fun load(url: String, title: String, playWhenReady: Boolean) {
        this.title = title
        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.playWhenReady = playWhenReady
        exoPlayer.prepare()
    }

    override fun play() {
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun stop() {
        exoPlayer.stop()
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun volume(): Float = exoPlayer.volume

    override fun speed() = exoPlayer.playbackParameters.speed

    override fun speed(speed: Float) {
        if (speed > 2f || speed < 0.1f) {
            Log.e("ExoPlayerImp", "Speed should be between 0.1f to 2f")
            return
        }
        exoPlayer.setPlaybackSpeed(speed)
    }
    override fun pitch() = exoPlayer.playbackParameters.pitch

    override fun pitch(pitch: Float) {
        if (pitch > 2f || pitch < 0.1f) {
            Log.e("ExoPlayerImp", "Pitch should be between 0.1f to 2f")
            return
        }
        exoPlayer.playbackParameters = PlaybackParameters(exoPlayer.playbackParameters.speed, pitch)
    }

    override fun setVolume(volume: Float) {
        exoPlayer.volume = volume
    }

    override fun setOnLoop(enabled: Boolean) {
        exoPlayer.repeatMode = if (enabled) ExoPlayer.REPEAT_MODE_ONE else ExoPlayer.REPEAT_MODE_OFF
    }

    override fun isOnLoop() = exoPlayer.repeatMode == ExoPlayer.REPEAT_MODE_ONE

    override fun title() = title

    override fun togglePlayPause() {
        if (isPlaying) pause() else play()
    }

    override val isPlaying: Boolean
        get() = exoPlayer.isPlaying

    override val state: SharedFlow<PlayerState>
        get() = _playerState.asSharedFlow()
}

internal class ExoPlayerUI(private val player: Player) : PlayerUi {
    override fun build(context: Context): View {
        return PlayerView(context).apply {
            setPlayer(
                (this@ExoPlayerUI.player as? ExoPlayerImp)?.exoPlayer
                    ?: error("Player is not ExoPlayer")
            )
            updateLayoutParams<LayoutParams> {
                width = LayoutParams.MATCH_PARENT
                height = LayoutParams.MATCH_PARENT
            }
        }
    }
}

object PlayerBuilder {
    fun build(context: Context): Player {
        return ExoPlayerImp(exoPlayer = ExoPlayer.Builder(context).build())
    }
}
