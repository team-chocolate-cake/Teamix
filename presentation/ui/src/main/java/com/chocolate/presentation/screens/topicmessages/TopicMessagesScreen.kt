package com.chocolate.presentation.screens.topicmessages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.composable.MessageCard
import com.chocolate.presentation.composable.StartNewMessage
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.theme.*
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.topicmessages.TopicMessagesEffect
import com.chocolate.viewmodel.topicmessages.TopicMessagesInteraction
import com.chocolate.viewmodel.topicmessages.TopicMessagesViewModel
import com.chocolate.viewmodel.topicmessages.TopicUiState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TopicMessageScreen(
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
    TopicMessageContent(topicUiState = state, viewModel, scrollState)
}


@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicMessageContent(
    topicUiState: TopicUiState,
    topicInteraction: TopicMessagesInteraction,
    scrollState: LazyListState
) {
    val systemUiController = rememberSystemUiController()
    val isDarkIcons = MaterialTheme.customColors().card == LightCard

    TeamixScaffold(
        title = topicUiState.topicName,
        hasAppBar = true,
        hasBackArrow = true,
        bottomBar = {
            StartNewMessage(
                onMessageInputChanged = { topicInteraction.onMessageInputChanged(it) },
                onSendMessage = { topicInteraction.onSendMessage(topicUiState.messageInput) },
                modifier = Modifier,
                messageInput = topicUiState.messageInput,
            )
        }
    ) { padding ->
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().card,
            darkIcons = isDarkIcons
        )

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
                items(topicUiState.messages.size, key = {
                    topicUiState.messages[it].id
                }) {
                    MessageCard(
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(top = SpacingXXMedium),
                        messageUiState = topicUiState.messages[it],
                        onSaveMessage = { topicInteraction.onSaveMessage(topicUiState.messages[it]) },
                        messageMaxLines = 2,
                        messageOverflow = TextOverflow.Ellipsis,
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
        TopicMessageScreen()
    }
}