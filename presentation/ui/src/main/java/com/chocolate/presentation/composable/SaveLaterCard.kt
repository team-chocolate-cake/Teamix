package com.chocolate.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.saveLater.MessageItemUiState

@Composable
fun SaveLaterCard(item: MessageItemUiState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(Radius12)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColors().card)
    ) {

    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(Space8)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = item.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .align(Alignment.CenterVertically)
        )
        Column(verticalArrangement = Arrangement.spacedBy(Space4)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.username)
                Text(text = item.time)
            }
            Text(text = item.messageContent, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}