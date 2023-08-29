package com.chocolate.presentation.screens.conversation

import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.MyReplyMessage
import com.chocolate.presentation.composable.ReplyMessage
import com.chocolate.presentation.composable.StartNewMessage
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.conversation.ConversationInteraction
import com.chocolate.viewmodel.conversation.ConversationUiState
import com.chocolate.viewmodel.conversation.ConversationViewModel

@Composable
fun ConversationScreen(
    viewModel: ConversationViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    ConversationContent(state,viewModel)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConversationContent(state: ConversationUiState,conversationInteraction: ConversationInteraction) {
    TeamixScaffold(
        title = state.username,
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        bottomBar = {
            StartNewMessage(
                openEmojisTile = { },
                onMessageInputChanged = { conversationInteraction.onMessageInputChanged(it) },
                onSendMessage = { conversationInteraction.onSendMessage() },
                onStartVoiceRecording = {  },
                onClickCamera = { },
                onClickPhotoOrVideo = { },
                photoOrVideoList = emptyList(),
                modifier = Modifier,
                messageInput = state.messageInput,
            )
        }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            val (messages) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(messages) {
                        bottom.linkTo(parent.bottom)
                    },
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(SpacingXLarge),
                contentPadding = PaddingValues(bottom = SpacingXLarge, top = SpacingXLarge)
            ) {
                items(state.messages.size) {
                    if (state.messages[it].isMyReplay)
                        MyReplyMessage(
                            messageUiState = state.messages[it],
                            onAddReactionToMessage = {  },
                            onGetNotification = {  },
                            onPinMessage = { },
                            onSaveMessage = {  },
                            onClickReact = { a,b->}
                        )
                    else
                        ReplyMessage(
                            messageUiState = state.messages[it],
                            onAddReactionToMessage = { },
                            onGetNotification = {  },
                            onPinMessage = {  },
                            onSaveMessage = {  },
                            onOpenReactTile = {  },
                            onClickReact = { a,b->}
                        )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ConversationPreview() {
    TeamixTheme() {
        ConversationScreen()
    }
}