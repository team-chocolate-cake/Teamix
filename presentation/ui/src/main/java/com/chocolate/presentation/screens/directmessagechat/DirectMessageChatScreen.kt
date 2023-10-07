package com.chocolate.presentation.screens.directmessagechat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.MessageCard
import com.chocolate.presentation.composable.StartNewMessage
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.createchannel.composable.ActionSnakeBar
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXMedium
import com.chocolate.viewmodel.directmessagechat.DirectMessagesChatViewModel
import com.chocolate.viewmodel.topicmessages.TopicMessagesInteraction
import com.chocolate.viewmodel.topicmessages.TopicUiState

@Composable
fun DirectMessageChatScreen(viewModel: DirectMessagesChatViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    DirectMessageChatContent(state, viewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectMessageChatContent(state: TopicUiState, interaction: TopicMessagesInteraction) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = state.messages.size) {
        state.messages.takeIf { messages ->
            messages.isNotEmpty()
        }?.let {
            scrollState.animateScrollToItem(0)
        }
    }
    TeamixScaffold(
        title = state.topicName,
        hasAppBar = true,
        hasBackArrow = true,
        bottomBar = {
            StartNewMessage(
                onMessageInputChanged = { interaction.onMessageInputChanged(it) },
                onSendMessage = { interaction.onSendMessage(state.messageInput) },
                modifier = Modifier,
                messageInput = state.messageInput,
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
                verticalArrangement = Arrangement.Bottom,
                contentPadding = PaddingValues(bottom = SpacingXLarge, top = SpacingXLarge)
            ) {
                items(state.messages.size) {
                    MessageCard(
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(top = SpacingXXMedium),
                        messageUiState = state.messages[it],
                        onSaveMessage = { interaction.onSaveMessage(state.messages[it]) },
                    )
                }
            }
            if (state.error == null && state.savedMessageState != null) {
                ActionSnakeBar(
                    isVisible = true,
                    contentMessage = state.savedMessageState.toString(),
                    isToggleButtonVisible = false,
                    actionTitle = stringResource(id = R.string.dismiss),
                    onDismiss = { interaction.onSnackBarDismiss() }
                )
            }
        }
    }
}