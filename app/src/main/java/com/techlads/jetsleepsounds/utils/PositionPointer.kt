package com.techlads.jetsleepsounds.utils

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset

object PositionPointer {
    val pointer = mutableStateOf(Offset(0f, 0f))
    val completeTour = mutableStateOf(true)
}