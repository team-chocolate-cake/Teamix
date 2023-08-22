package com.chocolate.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun FitImage(
    modifier: Modifier,
    image: Painter,
    contentDescription: String,
) {
    val imageRatio = image.intrinsicSize.width / image.intrinsicSize.height
    Image(
        modifier = modifier.aspectRatio(imageRatio),
        painter = image,
        contentDescription = contentDescription,
    )
}