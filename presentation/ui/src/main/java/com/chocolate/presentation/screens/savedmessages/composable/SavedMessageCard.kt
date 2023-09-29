package com.chocolate.presentation.screens.savedmessages.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.SpacingMassive
import com.chocolate.presentation.theme.SpacingXXSmall
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.savedmessage.MessageItemUiState

@Composable
fun SaveLaterCard(item: MessageItemUiState, painter: Painter) {
    val colors = MaterialTheme.customColors()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(Radius12)),
        colors = CardDefaults.cardColors(containerColor = colors.card)
    ) {


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpacingMedium),
            horizontalArrangement = Arrangement.spacedBy(SpacingMedium)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(SpacingMassive)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Column(verticalArrangement = Arrangement.spacedBy(SpacingXXSmall)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.username,
                        color = colors.onBackground87,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = item.time,
                        color = colors.onBackground60,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Text(
                    text = item.messageContent,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.onBackground60,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SaveLaterCard(
        item = MessageItemUiState(
            id = "50",
            imageUrl = "https://i.pinimg.com/originals/bf/31/9c/bf319cbf55fa59d5e7516506900a3144.jpg",
            messageContent = "content",
            time = "13:40",
            username = "kareem"
        ),
        painter = painterResource(id = R.drawable.ownerpowers)
    )
}