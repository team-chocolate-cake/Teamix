package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.theme.SpacingMassive
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.directmessage.ChatUiState

@Composable
fun DirectMessageChat(state: ChatUiState , modifier: Modifier){
    val colors = MaterialTheme.customColors()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.card)
            .padding(
                top = 14.dp,
                bottom = 14.dp,
                end = 10.dp,
                start = SpacingXMedium
            )
    ) {
        Row() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.image).build(),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(SpacingMassive),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = SpacingXMedium)
            ) {
                Text(
                    text = state.name,
                    style = MaterialTheme.typography.labelMedium,
                    color = colors.onBackground87
                )
                Text(
                    text = state.lastMessage,
                    style = MaterialTheme.typography.labelSmall,
                    color = colors.onBackground60,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = state.lastMessageDate,
                style = MaterialTheme.typography.labelSmall,
                color = colors.onBackground60
            )
        }
    }
}