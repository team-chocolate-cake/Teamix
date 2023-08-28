package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.theme.Float1
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraftCard(
    modifier: Modifier = Modifier,
    id: Int,
    time: String,
    messageContent: String,
    isInStream: Boolean = false,
    topicName: String = "",
    onClickMessage: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(Radius12)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColors().card),
        onClick = { onClickMessage(id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpacingXMedium)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(SpacingMedium),
                modifier = Modifier.padding(start = SpacingXMedium)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = time,
                        color = MaterialTheme.customColors().onBackground60,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Text(
                    text = messageContent,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.customColors().onBackground60,
                    style = MaterialTheme.typography.labelSmall
                )
                Spacer(modifier = Modifier.weight(Float1))
                AnimatedVisibility(visible = isInStream) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(SpacingXLarge)
                    ) {
                        Text(
                            text = topicName,
                            color = MaterialTheme.customColors().primary,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SavedLaterCardPreview() {
    val image = "https://images.pexels.com/photos/1222271/pexels-photo-1222271.jpeg"
    DraftCard(
        id = 0,
        time = "",
        messageContent = "",
        isInStream = true,
        topicName = "",
        onClickMessage = {},)
}