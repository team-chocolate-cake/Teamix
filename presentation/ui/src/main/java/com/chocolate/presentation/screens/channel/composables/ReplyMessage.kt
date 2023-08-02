package com.chocolate.presentation.screens.channel.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space40
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ReplyMessage(
    username: String = "Mimo Mimo",
    imageRes: Int = R.drawable.person,
    replayDate: String = "6:08 AM",
    reactions: List<Reaction> = listOf(
        Reaction(R.drawable.heart, 12),
        Reaction(R.drawable.heart, 12),
    ),
    message: String = "----- New day ----\nGood Things Take Time A few men were despatched to poke around in the warm, dark tunnels on either side of Odéon station, where "
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        MessageOptions(){
            showSheet = false
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    showSheet = true
                }
            )
            .padding(horizontal = Space8 , vertical = Space16)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageRes).build(),
            modifier = Modifier
                .clip(CircleShape)
                .size(Space40),
            contentDescription = "Bread"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Space8)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Space8),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = username,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.customColors().onBackground87
                )
                Text(
                    text = replayDate,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.customColors().onBackground60
                )
            }


            Text(
                text = message,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.customColors().onBackground60
            )


            if (!reactions.isEmpty()) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Space8),
                ) {
                    reactions.forEach { reaction ->
                        ReactionButton(reaction) { onclick ->

                        }
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = Space4)
                            .clip(RoundedCornerShape(100.dp))
                            .background(
                                MaterialTheme.customColors().lightGray
                            )
                            .clickable {
                                //todo open reaction list
                            }
                            .padding(vertical = Space4, horizontal = Space8)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(R.drawable.add_reaction).build(),
                            contentDescription = "Reaction",
                            modifier = Modifier
                                .size(Space16)
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview(showSystemUi = true)
fun ReplyMessageReview() {
    TeamixTheme {
        ReplyMessage()
    }
}