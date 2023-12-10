package com.techlads.jetsleepsounds.mixer

import androidx.compose.runtime.mutableStateListOf
import com.techlads.jetsleepsounds.player.Player
import com.techlads.jetsleepsounds.player.PlayerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


object Mixer {
    val players = mutableStateListOf<Player>()

    suspend fun addPlayer(player: Player) {
        coroutineScope {
            players.add(player)
            launch {
                player.state.collect {
                    if (it == PlayerState.Ended) {
                        removePlayer(player)
                    }
                }
            }
        }
    }

    fun removePlayer(player: Player) {
        player.stop()
        player.release()
        players.remove(player)
    }
}