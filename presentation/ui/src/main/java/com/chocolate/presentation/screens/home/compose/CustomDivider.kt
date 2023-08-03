package com.chocolate.presentation.screens.home.compose

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.customColors

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    thickness: Int = 1,
    color: Color = MaterialTheme.customColors().border
) {
    Divider(
        modifier = modifier,
        thickness = thickness.dp,
        color = color
    )
}