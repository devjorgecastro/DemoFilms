package com.devstro.demofilms.feature.home.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerBox(modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val colors = listOf(
        primary.copy(alpha = 0.4f),
        primary.copy(alpha = 0.5f),
        primary.copy(alpha = 0.3f),
        primary.copy(alpha = 0.6f),
        primary.copy(alpha = 0.7f),
        primary.copy(alpha = 0.6f),
        primary.copy(alpha = 0.5f),
        primary.copy(alpha = 0.4f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = modifier
            .size(150.dp, 250.dp)
            .background(
                Brush.linearGradient(
                    colors = colors,
                    start = Offset(translateAnimation - 1200, 0f),
                    end = Offset(translateAnimation, 0f)
                )
            )
    )
}
