package com.chocolate.presentation.screens.topicMessages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.chocolate.presentation.theme.*
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.topicMessages.TopicMessagesEffect
import com.chocolate.viewmodel.topicMessages.TopicMessagesInteraction
import com.chocolate.viewmodel.topicMessages.TopicUiState
import com.chocolate.viewmodel.topicMessages.TopicMessagesViewModel

@Composable
fun TopicScreen(
    viewModel: TopicMessagesViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    val scrollState = rememberLazyListState()
    CollectUiEffect(viewModel.effect) { effect ->
        when (effect) {
            TopicMessagesEffect.NavigationBack -> navController.popBackStack()
        }
    }
    TopicContent(topicUiState = state, viewModel, scrollState)
}


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicContent(
    topicUiState: TopicUiState,
    topicInteraction: TopicMessagesInteraction,
    scrollState: LazyListState
) {
    LaunchedEffect(key1 = topicUiState.messages.size) {
        topicUiState.messages.takeIf { messages ->
            messages.isNotEmpty()
        }?.let {
            val lastIndex = it.size - 1
            val scrollToIndex = lastIndex.coerceAtLeast(0)
            scrollState.animateScrollToItem(0)
        }

    }

    TeamixScaffold(
        title = topicUiState.topicName,
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
        hasBackArrow = true,
        bottomBar = {
            StartNewMessage(
                openEmojisTile = { },
                onMessageInputChanged = { topicInteraction.onMessageInputChanged(it) },
                onSendMessage = { topicInteraction.onSendMessage(topicUiState.messageInput) },
                onStartVoiceRecording = {  },
                onClickCamera = { },
                onClickPhotoOrVideo = {  },
                photoOrVideoList = topicUiState.photoAndVideo,
                modifier = Modifier,
                messageInput = topicUiState.messageInput,
            )
        }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val (messages) = createRefs()
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(messages) { bottom.linkTo(parent.bottom) },
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(SpacingXLarge),
                contentPadding = PaddingValues(bottom = SpacingXLarge, top = SpacingXLarge)
            ) {
                items(topicUiState.messages.size, key = {
                    topicUiState.messages[it].id
                }) {
                    if (topicUiState.messages[it].isMyReplay)
                        MyReplyMessage(
                            modifier = Modifier.animateItemPlacement(),
                            messageUiState = topicUiState.messages[it],
                            onAddReactionToMessage = {  },
                            onGetNotification = {  },
                            onPinMessage = { },
                            onSaveMessage = { topicInteraction.onSaveMessage(topicUiState.messages[it]) },
                            onClickReact = { positive, state ->

                            }
                        )
                    else
                        ReplyMessage(
                            modifier = Modifier.animateItemPlacement(),
                            messageUiState = topicUiState.messages[it],
                            onAddReactionToMessage = { },
                            onGetNotification = {  },
                            onPinMessage = {  },
                            onSaveMessage = { topicInteraction.onSaveMessage(topicUiState.messages[it]) },
                            onOpenReactTile = { },
                            onClickReact = { positive, state ->

                            }
                        )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun TopicPreview() {
    TeamixTheme() {
        TopicScreen()
    }
}