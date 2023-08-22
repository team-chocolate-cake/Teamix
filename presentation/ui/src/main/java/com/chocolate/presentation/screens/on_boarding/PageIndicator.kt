package com.chocolate.presentation.screens.on_boarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    numberOfPages: Int,
    selectedPage: Int ,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        for (i in 0 until numberOfPages) {
            val isSelected = i == selectedPage
            PageIndicatorItem(
                isSelected = isSelected,
            )
        }
    }
}

@Composable
private fun PageIndicatorItem(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors=MaterialTheme.customColors()
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            colors.primary
        } else {
            colors.onSecondary
        },
        animationSpec = tween(
            durationMillis = 300,
        ), label = stringResource(R.string.animatecolor)
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            36.dp
        } else {
            8.dp
        },
        animationSpec = tween(
            durationMillis = 300,
        ), label = stringResource(R.string.animatesize)
    )

    Canvas(
        modifier = modifier.size(width = width, height = 8.dp),
    ) {
        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(
                width = width.toPx(),
                height = 8.dp.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = 8.dp.toPx(),
                y = 8.dp.toPx(),
            ),
        )
    }
}