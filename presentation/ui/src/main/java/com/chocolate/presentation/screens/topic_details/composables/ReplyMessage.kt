package com.chocolate.presentation.screens.topic_details.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space0
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space40
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ReplyMessage(
    username: String = "Mimo Mimo",
    userImage: Int = R.drawable.person,
    replayDate: String = "16:46",
    messageImage: Int? = R.drawable.image_placeholder,
    reactions: List<Reaction> = listOf(
        Reaction(R.drawable.heart, 12),
        Reaction(R.drawable.heart, 12),
    ),
    isMyReplay: Boolean = false,
    message: String = "----- New day ----\nGood Things Take Time A few men were despatched to poke around in the warm, dark tunnels on either side of Odéon station, where "
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        MessageOptionsBottomSheet() {
            showSheet = false
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = if (!isMyReplay) Space24 else Space0,
                start = if (!isMyReplay) Space0 else Space24,
            )
            .padding(horizontal = Space8)
    ) {
        val (image, messageCard, emojis) = createRefs()

        if (!isMyReplay) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(userImage).build(),
                modifier = Modifier
                    .padding(end = Space8)
                    .clip(CircleShape)
                    .size(Space40)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        if (!reactions.isEmpty()) bottom.linkTo(emojis.top)
                        else bottom.linkTo(parent.bottom)
                    },
                contentDescription = ""
            )
        }
        Card(colors = CardDefaults.cardColors(
            containerColor = if (!isMyReplay) Color.White else MaterialTheme.customColors().primary //todo must be changed
        ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = if (!isMyReplay) Alignment.CenterStart else Alignment.CenterEnd)
                .padding(
                    end = if (!isMyReplay) Space24 else 0.dp
                )
                .constrainAs(messageCard) {
                    if (!isMyReplay) start.linkTo(image.end)
                    else end.linkTo(parent.end)
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomEnd = if (!isMyReplay) 12.dp else 0.dp,
                        bottomStart = if (!isMyReplay) 0.dp else 12.dp
                    )
                )
                .combinedClickable(onClick = {}, onLongClick = {
                    showSheet = true
                }),
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp,
                bottomEnd = if (!isMyReplay) 12.dp else 0.dp,
                bottomStart = if (!isMyReplay) 0.dp else 12.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(Space8)
            ) {
                if (!isMyReplay) {
                    Text(
                        text = username,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.customColors().primary
                    )
                }
                if (messageImage != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(messageImage).build(),
                        modifier = Modifier
                            .padding(bottom = Space4)
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(Space8)),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                }

                Text(
                    textAlign = if(!isMyReplay) TextAlign.Start else TextAlign.End,
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (!isMyReplay) MaterialTheme.customColors().onBackground87 else Color.White
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = Space4),
                    textAlign = if(!isMyReplay) TextAlign.Start else TextAlign.End,
                    text = replayDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (!isMyReplay) MaterialTheme.customColors().onBackground87 else Color.White
                )
            }

        }

        if (!reactions.isEmpty()) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(Space8),
                modifier = Modifier.constrainAs(emojis) {
                    start.linkTo(image.end)
                    top.linkTo(messageCard.bottom)
                }) {
                reactions.forEach { reaction ->
                    ReactionButton(reaction) { onclick ->

                    }
                }
                if (!isMyReplay) {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = Space4)
                            .clip(RoundedCornerShape(100.dp))
                            .background(
                                MaterialTheme.customColors().lightGray
                            )
                            .clickable {
                                //todo open reaction list
                            }
                            .padding(vertical = Space4, horizontal = Space8)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(R.drawable.add_reaction).build(),
                            contentDescription = "Reaction",
                            modifier = Modifier.size(Space16)
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