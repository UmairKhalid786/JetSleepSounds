@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.techlads.jetsleepsounds.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.techlads.jetsleepsounds.utils.PositionPointer.completeTour
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


fun Modifier.gradientOverlay(gradientColor: Color) = drawWithCache {

    val horizontalGradient = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent, Color.Transparent,

            gradientColor.copy(alpha = 0.6f)
        )
    )
    val topBottom = Brush.verticalGradient(
        colors = listOf(
            gradientColor.copy(alpha = 0.6f), Color.Transparent, Color.Transparent
        )
    )

    onDrawWithContent {
        drawContent()
        drawRect(horizontalGradient)
        drawRect(topBottom)
    }
}

@Composable
fun Modifier.pointerOverlay(gradientColor: Color = MaterialTheme.colorScheme.primary): Modifier {

    val position by remember { PositionPointer.pointer }
    val completeTour by remember { PositionPointer.completeTour }

    LaunchedEffect(Unit) {
        delay(2.seconds)
        PositionPointer.completeTour.value = false
    }

    return drawWithCache {
        onDrawWithContent {
            drawContent()
            if (!completeTour) {
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, gradientColor),
                        center = position,
                        radius = 100.dp.toPx(),
                    )
                )
            }
        }
    }
}

@Composable
fun Modifier.pointMeAfter(time: Duration, thenCompleteTour: Boolean = false): Modifier {
    val scope = rememberCoroutineScope()
    val alreadyDealt = rememberSaveable {
        mutableStateOf(false)
    }

    return onGloballyPositioned {
        if (!alreadyDealt.value) {
            scope.launch {
                delay(time)
                val width = it.size.width
                val height = it.size.height
                PositionPointer.pointer.value = Offset(
                    it.positionInRoot().x + width.div(2),
                    it.positionInRoot().y + height.div(2)
                )
                alreadyDealt.value = true

                if (thenCompleteTour) {
                    delay(3.seconds)
                    completeTour.value = true
                }
            }
        }
    }
}