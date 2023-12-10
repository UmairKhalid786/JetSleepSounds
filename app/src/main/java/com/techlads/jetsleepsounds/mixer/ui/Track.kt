@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.mixer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.mixer.Mixer
import com.techlads.jetsleepsounds.player.Player
import kotlinx.coroutines.launch


@Composable
fun Track(player: Player, onClick: (Player) -> Unit) {
    val scope = rememberCoroutineScope()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.6f), shape = MaterialTheme.shapes.medium)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        IconButton(onClick = {
            scope.launch {
                Mixer.removePlayer(player)
            }
        }) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Remove",
                modifier = Modifier.size(20.dp)
            )
        }

        Text(text = player.title(), Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)

        IconButton(onClick = {
            onClick(player)
        }) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}