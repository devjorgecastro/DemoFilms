package com.devstro.demofilms.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalFilmsPlaceholder(itemWidth: Dp, itemHeight: Dp) {
    LazyRow {
        items(5) {
            Card(
                modifier = Modifier
                    .size(itemWidth, itemHeight)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp
                    )
            ) {
                ShimmerBox()
            }
        }
    }
}

