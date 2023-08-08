package com.chocolate.presentation.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun FitImage(
    modifier: Modifier,
    @DrawableRes drawableResource: Int,
    contentDescription: String,
) {
    val painter = painterResource(id = drawableResource)
    val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
    Image(
        modifier = modifier.aspectRatio(imageRatio),
        painter = painter,
        contentDescription = contentDescription,
    )
}