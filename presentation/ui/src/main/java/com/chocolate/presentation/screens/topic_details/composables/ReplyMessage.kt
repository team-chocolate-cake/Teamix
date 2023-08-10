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
import com.chocolate.presentation.screens.topic_details.MessageUiState
import com.chocolate.presentation.screens.topic_details.ReactionUiState
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
    messageUiState: MessageUiState,
    onAddReactionToMessage: (Int) -> Unit,
    onSaveMessage: () -> Unit,
    onGetNotification: () -> Unit,
    onPinMessage: () -> Unit,
    onOpenReactTile: () -> Unit,
    onClickReact: (Boolean,ReactionUiState) -> Unit,
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        MessageOptionsBottomSheet(
            onAddReactionToMessage = onAddReactionToMessage,
            onGetNotification =onGetNotification ,
            onPinMessage = onPinMessage,
            onSaveMessage =onSaveMessage
        ) {
            showSheet = false
        }
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = if (!messageUiState.isMyReplay) Space24 else Space0,
                start = if (!messageUiState.isMyReplay) Space0 else Space24,
            )
            .padding(horizontal = Space8)
    ) {
        val (image, messageCard, emojis) = createRefs()

        if (!messageUiState.isMyReplay) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(messageUiState.userImage).build(),
                modifier = Modifier
                    .padding(end = Space8)
                    .clip(CircleShape)
                    .size(Space40)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        if (!messageUiState.reactions.isEmpty()) bottom.linkTo(emojis.top)
                        else bottom.linkTo(parent.bottom)
                    },
                contentDescription = ""
            )
        }
        Card(colors = CardDefaults.cardColors(
            containerColor = if (!messageUiState.isMyReplay) Color.White else MaterialTheme.customColors().primary
        ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = if (!messageUiState.isMyReplay) Alignment.CenterStart else Alignment.CenterEnd)
                .padding(
                    end = if (!messageUiState.isMyReplay) Space24 else 0.dp
                )
                .constrainAs(messageCard) {
                    if (!messageUiState.isMyReplay) start.linkTo(image.end)
                    else end.linkTo(parent.end)
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomEnd = if (!messageUiState.isMyReplay) 12.dp else 0.dp,
                        bottomStart = if (!messageUiState.isMyReplay) 0.dp else 12.dp
                    )
                )
                .combinedClickable(onClick = {}, onLongClick = {
                    showSheet = true
                }),
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp,
                bottomEnd = if (!messageUiState.isMyReplay) 12.dp else 0.dp,
                bottomStart = if (!messageUiState.isMyReplay) 0.dp else 12.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(Space8)
            ) {
                if (!messageUiState.isMyReplay) {
                    Text(
                        text = messageUiState.username,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.customColors().primary
                    )
                }
                if (messageUiState.messageImage != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(messageUiState.messageImage)
                            .build(),
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
                    textAlign = if (!messageUiState.isMyReplay) TextAlign.Start else TextAlign.End,
                    text = messageUiState.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (!messageUiState.isMyReplay) MaterialTheme.customColors().onBackground87 else Color.White
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Space4),
                    textAlign = if (!messageUiState.isMyReplay) TextAlign.Start else TextAlign.End,
                    text = messageUiState.replayDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (!messageUiState.isMyReplay) MaterialTheme.customColors().onBackground87 else Color.White
                )
            }

        }

        if (!messageUiState.reactions.isEmpty()) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(Space8),
                modifier = Modifier.constrainAs(emojis) {
                    start.linkTo(image.end)
                    top.linkTo(messageCard.bottom)
                }) {
                messageUiState.reactions.forEach { reaction ->
                    ReactionButton(reaction) { clicked, reaction ->
                        onClickReact(clicked,reaction)
                    }
                }
                if (!messageUiState.isMyReplay) {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = Space4)
                            .clip(RoundedCornerShape(100.dp))
                            .background(
                                MaterialTheme.customColors().lightGray
                            )
                            .clickable {
                                onOpenReactTile()
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