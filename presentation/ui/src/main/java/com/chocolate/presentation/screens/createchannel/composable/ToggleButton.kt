package com.chocolate.presentation.screens.createchannel.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.theme.Border1
import com.chocolate.presentation.theme.ButtonSize48
import com.chocolate.presentation.theme.Radius8
import com.chocolate.presentation.theme.SpacingMedium

@Composable
fun ToggleButton(
    modifier: Modifier = Modifier,
    isFilled: Boolean = false,
    color: Color ,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .height(ButtonSize48),
        onClick = onClick,
        contentPadding = PaddingValues(bottom = SpacingMedium),
        shape = RoundedCornerShape(Radius8),
        colors = if(isFilled) ButtonDefaults.buttonColors(containerColor = color) else ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(width = Border1, color = color)
    ) {
        content()
    }
}

@Preview
@Composable
private fun ToggleButtonPrev() {
    ToggleButton(onClick = {  }, color = MaterialTheme.colorScheme.primary, isFilled = false) {
        Text(text = "private", color = MaterialTheme.colorScheme.onPrimary)
    }
}
