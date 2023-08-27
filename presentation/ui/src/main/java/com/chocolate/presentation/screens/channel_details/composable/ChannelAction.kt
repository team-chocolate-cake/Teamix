package com.chocolate.presentation.screens.channel_details.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.customColors

@Composable
fun ChannelAction(
    icon:Painter,
    text:String,
    onItemClicked:()->Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(SpacingXLarge))
            .background(Color.White)
            .clickable {
                onItemClicked()
            }
            .padding(vertical = SpacingXLarge),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = icon,
                contentDescription = "",
                tint = MaterialTheme.customColors().onBackground60,
                modifier = Modifier.padding(bottom = SpacingMedium)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.customColors().onBackground60
            )
        }
    }
}
