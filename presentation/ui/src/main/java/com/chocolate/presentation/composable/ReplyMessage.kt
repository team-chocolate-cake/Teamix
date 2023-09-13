package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingMassive
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingTiny
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.topic.MessageUiState
import com.chocolate.viewmodel.topic.ReactionUiState

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun ReplyMessage(
    modifier: Modifier = Modifier,
    messageUiState: MessageUiState,
    onAddReactionToMessage: (Int) -> Unit,
    onSaveMessage: () -> Unit,
    onGetNotification: () -> Unit,
    onPinMessage: () -> Unit,
    onOpenReactTile: () -> Unit,
    onClickReact: (Boolean, ReactionUiState) -> Unit,
) {
    var showSheet by remember { mutableStateOf(false) }
    AnimatedVisibility(showSheet) {
        MessageOptionsBottomSheet(
            onAddReactionToMessage = onAddReactionToMessage,
            onGetNotification = onGetNotification,
            onPinMessage = onPinMessage,
            onSaveMessage = onSaveMessage
        ) {
            showSheet = false
        }
    }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                end = SpacingXXLarge,
                start = SpacingTiny
            )
            .padding(horizontal = SpacingXMedium)
    ) {
        val (image, messageCard, emojis) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(messageUiState.userImage)
                .build(),
            modifier = Modifier
                .padding(end = SpacingXMedium)
                .clip(CircleShape)
                .size(SpacingMassive)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    if (messageUiState.reactions.isNotEmpty()) bottom.linkTo(emojis.top)
                    else bottom.linkTo(parent.bottom)
                },
            contentDescription = ""
        )

        Card(colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.CenterStart)
                .padding(
                    end = SpacingXXLarge
                )
                .constrainAs(messageCard) {
                    start.linkTo(image.end)
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomEnd = 12.dp,
                        bottomStart = 0.dp
                    )
                )
                .combinedClickable(onClick = {}, onLongClick = {
                    showSheet = true
                }),
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp,
                bottomEnd = 12.dp,
                bottomStart = 0.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(SpacingXMedium)
            ) {

                Text(
                    text = messageUiState.username,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.customColors().primary
                )

                AnimatedVisibility(messageUiState.messageImageUrl.isNotEmpty()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(messageUiState.messageImageUrl)
                            .build(),
                        modifier = Modifier
                            .padding(bottom = SpacingMedium)
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(SpacingXMedium)),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )
                }

                Text(
                    textAlign = TextAlign.Start,
                    text = messageUiState.message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.customColors().onBackground87
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = SpacingMedium),
                    textAlign = TextAlign.Start,
                    text = messageUiState.replayDate,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.customColors().onBackground87
                )
            }

        }

        AnimatedVisibility(messageUiState.reactions.isNotEmpty()) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(SpacingXMedium),
                modifier = Modifier.constrainAs(emojis) {
                    start.linkTo(image.end)
                    top.linkTo(messageCard.bottom)
                }) {
                messageUiState.reactions.forEach { reaction ->
                    ReactionButton(reaction) { clicked, reaction ->
                        onClickReact(clicked, reaction)
                    }
                }

                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(vertical = SpacingMedium)
                        .clip(RoundedCornerShape(100.dp))
                        .background(
                            MaterialTheme.customColors().lightGray
                        )
                        .clickable {
                            onOpenReactTile()
                        }
                        .padding(vertical = SpacingMedium, horizontal = SpacingXMedium)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.add_reaction).build(),
                        contentDescription = "Reaction",
                        modifier = Modifier.size(SpacingXLarge)
                    )
                }
            }
        }
    }

}