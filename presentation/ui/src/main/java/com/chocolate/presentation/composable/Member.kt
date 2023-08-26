package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.screens.channel_details.ChannelMemberUiState
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors

@Composable
fun Member(
    memberUiState: ChannelMemberUiState,
    contentDescription:String?=null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 86.dp, height = 76.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.customColors().card),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(memberUiState.name).build(),
                modifier = Modifier.padding(top = SpacingXMedium)
                    .clip(CircleShape)
                    .size(40.dp),
                contentDescription = contentDescription
            )
            Text(
                modifier = Modifier
                    .padding(top = SpacingMedium),
                textAlign = TextAlign.Center,
                text = memberUiState.Image,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.customColors().onBackground87
            )

        }
    }
}