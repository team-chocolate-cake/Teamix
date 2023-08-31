package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors


@Composable
fun SwipeBackground(
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    painter: Painter = painterResource(id = R.drawable.ic_remove),
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.customColors().red, shape = RoundedCornerShape(Radius12)
            )
            .padding(SpacingXLarge),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painter,
            tint = MaterialTheme.customColors().card,
            contentDescription = contentDescription
        )
    }
}