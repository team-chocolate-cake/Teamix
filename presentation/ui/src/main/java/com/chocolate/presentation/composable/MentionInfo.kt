package com.chocolate.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.OnLightBackground60
import com.chocolate.presentation.theme.OnLightBackground87
import com.chocolate.presentation.theme.OnLightPrimary
import com.chocolate.presentation.theme.OnLightSecondary38
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space40
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.Typography
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.mentions.state.MentionInfoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentionInfo(
    mentionInfo: MentionInfoUiState,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp),
        colors = CardDefaults.cardColors(MaterialTheme.customColors().onPrimary),
        shape = RoundedCornerShape(Space16),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(Space8),
            horizontalArrangement = Arrangement.spacedBy(Space8),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                modifier = Modifier
                    .size(Space40)
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(mentionInfo.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(modifier = Modifier.fillMaxHeight()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        mentionInfo.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().onBackground87
                    )
                    Text(
                        mentionInfo.time,
                        style = MaterialTheme.typography.labelSmall,
                        color =  MaterialTheme.customColors().onBackground60
                    )
                }

                Text(
                    mentionInfo.message,
                    style =MaterialTheme.typography.labelSmall,
                    color =  MaterialTheme.customColors().onBackground60,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        "#" + mentionInfo.channelName,
                        style =MaterialTheme.typography.labelSmall,
                        color =  MaterialTheme.customColors().onSecondary38,
                    )
                    Text(
                        mentionInfo.topicName,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.customColors().onSecondary38,
                    )
                }
            }
        }
    }
}