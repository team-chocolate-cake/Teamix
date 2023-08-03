package com.chocolate.presentation.screens.home.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.customColors

@Composable
fun CustomDivider(modifier: Modifier = Modifier, thickness: Int = 1) {
    val colors = MaterialTheme.customColors()
    Divider(
        modifier = modifier
            .fillMaxWidth(),
        thickness = thickness.dp,
        color = colors.border
    )


}