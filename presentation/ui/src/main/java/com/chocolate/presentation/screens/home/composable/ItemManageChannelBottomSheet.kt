package com.chocolate.presentation.screens.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium

@Composable
fun ItemManageChannelBottomSheet(
    colors: CustomColorsPalette,
    title: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    itemColor: Color = colors.onBackground60,
    onClickItemManageChannelBottomSheet: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(SpacingXLarge)
            .clickable { onClickItemManageChannelBottomSheet() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = title,
            tint = itemColor,
            modifier = Modifier.padding(end = SpacingXMedium)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = itemColor
        )
    }
}