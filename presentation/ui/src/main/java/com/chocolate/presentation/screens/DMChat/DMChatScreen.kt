package com.chocolate.presentation.screens.DMChat

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.MyReplyMessage
import com.chocolate.presentation.composable.ReplyMessage
import com.chocolate.presentation.composable.StartNewMessage
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.viewmodel.DMChat.DMChatViewModel
import com.chocolate.viewmodel.topic.TopicInteraction
import com.chocolate.viewmodel.topic.TopicUiState

@Composable
fun DMChatScreen(viewModel: DMChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    DMChatContent(state , viewModel)
}

@Composable
fun DMChatContent(state: TopicUiState, interaction: TopicInteraction) {
    TeamixScaffold(
        title = state.topicName,
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        bottomBar = {
            StartNewMessage(
                openEmojisTile = { interaction.openEmojisTile() },
                onMessageInputChanged = { interaction.onMessageInputChanged(it) },
                onSendMessage = { interaction.onSendMessage() },
                onStartVoiceRecording = { interaction.onStartVoiceRecording() },
                onClickCamera = { interaction.onClickCamera() },
                onClickPhotoOrVideo = { interaction.onClickPhotoOrVideo(it) },
                photoOrVideoList = state.photoAndVideo,
                modifier = Modifier,
                messageInput = state.messageInput,
            )
        }
    ) { padding ->
        ConstraintLayout(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {
            val (messages) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(messages) { bottom.linkTo(parent.bottom) },
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(SpacingXLarge),
                contentPadding = PaddingValues(bottom = SpacingXLarge, top = SpacingXLarge)
            ) {
                items(state.messages.size) {
                    if (state.messages[it].isMyReplay)
                        MyReplyMessage(
                            messageUiState = state.messages[it],
                            onAddReactionToMessage = { interaction.onAddReactionToMessage(it) },
                            onGetNotification = { interaction.onGetNotification() },
                            onPinMessage = { interaction.onPinMessage() },
                            onSaveMessage = { interaction.onSaveMessage() },
                            onClickReact = { positive, state ->
                                interaction.onClickReact(positive, state)
                            }
                        )
                    else
                        ReplyMessage(
                            messageUiState = state.messages[it],
                            onAddReactionToMessage = { interaction.onAddReactionToMessage(it) },
                            onGetNotification = { interaction.onGetNotification() },
                            onPinMessage = { interaction.onPinMessage() },
                            onSaveMessage = { interaction.onSaveMessage() },
                            onOpenReactTile = { interaction.onOpenReactTile() },
                            onClickReact = { positive, state ->
                                interaction.onClickReact(positive, state)
                            }
                        )
                }
            }
        }
    }
}