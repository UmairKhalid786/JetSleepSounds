package com.techlads.jetsleepsounds.mixer.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults

@Composable
fun Preference(
    modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit
) {
    var hashFocus by remember { mutableStateOf(false) }

    val animatedScale by animateFloatAsState(if (hashFocus) 1.01f else 1f)
    Surface(
        modifier = modifier
            .scale(animatedScale)
            .onFocusChanged {
                hashFocus = it.hasFocus
            },
        colors = SurfaceDefaults.colors(
            containerColor = Color.Black.copy(alpha = 0.6f),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = MaterialTheme.shapes.medium,
        border = if (hashFocus) Border(
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface),
            shape = MaterialTheme.shapes.medium
        ) else Border(
            border = BorderStroke(2.dp, Color.Transparent), shape = MaterialTheme.shapes.medium
        ),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) { content() }
    }
}
