package com.chocolate.presentation.screens.create_channel.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chocolate.presentation.theme.ButtonSize32
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.customColors

@Composable
fun ActionSnakeBar(
    contentMessage: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onClick: () -> Unit,
    actionTitle: String,
) {
    val colors = MaterialTheme.customColors()
    val textStyle = MaterialTheme.typography

    Box(
        modifier = modifier.fillMaxSize().padding(Space16)
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            visible = isVisible
        ) {
            Snackbar(shape = RoundedCornerShape(Radius12),) {
                Row(
                    modifier = Modifier.padding(vertical = Space4),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.weight(3f),text = contentMessage, style = textStyle.bodyMedium)
                    ToggleButton(
                        modifier = Modifier.height(ButtonSize32).weight(1f),
                        color = colors.primary,
                        isFilled = true,
                        onClick = { onClick() }) {
                        Text(
                            text = actionTitle,
                            style = textStyle.bodyMedium,
                            color = colors.card
                        )
                    }
                }
            }
        }
    }

}