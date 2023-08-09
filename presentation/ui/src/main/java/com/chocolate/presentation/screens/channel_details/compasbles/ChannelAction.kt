package com.chocolate.presentation.screens.channel_details.compasbles

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.customColors

@Composable
fun ChannelAction(
    channelAction: ChannelAction
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Space16))
            .background(Color.White)
            .clickable {
                channelAction.action()
            }
            .padding(vertical = Space16),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = channelAction.icon),
                contentDescription = "",
                tint = MaterialTheme.customColors().onBackground60,
                modifier = Modifier.padding(bottom = Space4)
            )
            Text(
                text = channelAction.text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.customColors().onBackground60
            )
        }
    }
}

data class ChannelAction(
    @DrawableRes val icon: Int,
    val text: String,
    val action: () -> Unit
)