package com.chocolate.presentation.mentions.composables

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.OnLightOnBackground60
import com.chocolate.presentation.theme.OnLightOnBackground87
import com.chocolate.presentation.theme.OnPrimary
import com.chocolate.presentation.theme.OnSecondary
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.Typography
import com.chocolate.viewmodel.mentions.state.MentionInfoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentionInfo(
    mentionInfo: MentionInfoUiState,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(88.dp),
        colors = CardDefaults.cardColors(OnPrimary),
        shape = RoundedCornerShape(Space16),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(Space8),
            horizontalArrangement = Arrangement.spacedBy(Space8),
            verticalAlignment = Alignment.Top
        ) {
            Image(painter = painterResource(0), contentDescription = null)
            Column(modifier = Modifier.fillMaxHeight()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        mentionInfo.name,
                        style = Typography.labelMedium,
                        color = OnLightOnBackground87
                    )
                    Text(
                        mentionInfo.time,
                        style = Typography.labelSmall,
                        color = OnLightOnBackground60
                    )
                }

                Text(
                    mentionInfo.message,
                    style = Typography.labelSmall,
                    color = OnLightOnBackground60,
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
                        style = Typography.labelSmall,
                        color = OnSecondary,
                    )
                    Text(
                        mentionInfo.topicName,
                        style = Typography.labelSmall,
                        color = OnSecondary,
                    )
                }
            }
        }
    }
}