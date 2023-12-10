@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import com.techlads.jetsleepsounds.R


@Composable
fun HomeHeader() {
    Row(
        Modifier
            .zIndex(10f)
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.moon),
            contentDescription = "Logo",
            colorFilter = tint(Color.White.copy(alpha = 0.8f)),
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(onClick = {  }) {
            Text(text = "Go Premium")
        }
    }
}
