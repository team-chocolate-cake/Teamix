package com.chocolate.presentation.screens.topic_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.chocolate.presentation.screens.channel.composables.CustomAppBar
import com.chocolate.presentation.screens.topic_details.composables.ReplyMessage
import com.chocolate.presentation.screens.topic_details.composables.StartNewMessage
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun TopicScreen() {
    TopicContent(
        topicScreenUiState = TopicScreenUiState(),
        navigationBack = {},
        openEmojisTile = {},
        onMessageInputChanged = {},
        OnSendMessage = {},
        OnStartVoiceRecording = {},
        onClickPhotoOrVideo = {},
        onClickCamera = {},
        onAddReactionToMessage = {},
        onGetNotification ={} ,
        onPinMessage = {},
        onSaveMessage ={}
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicContent(
    topicScreenUiState: TopicScreenUiState,
    navigationBack: () -> Unit,
    openEmojisTile: () -> Unit,
    onMessageInputChanged: (String) -> Unit,
    OnSendMessage: () -> Unit,
    OnStartVoiceRecording: () -> Unit,
    onClickCamera: () -> Unit,
    onClickPhotoOrVideo: (Int) -> Unit,
    onAddReactionToMessage: (Int) -> Unit,
    onSaveMessage: () -> Unit,
    onGetNotification: () -> Unit,
    onPinMessage: () -> Unit,

    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.customColors().background,//todo change this color to be in dark theme
        topBar = {
            //todo this app bar must be changed to be one composable for all screens
            CustomAppBar(
                title = topicScreenUiState.topicName,
                navigationBack = navigationBack,
            )
        },
        bottomBar = {
            StartNewMessage(
                openEmojisTile = openEmojisTile,
                onMessageInputChanged = onMessageInputChanged,
                onSendMessage = OnSendMessage,
                onStartVoiceRecording = OnStartVoiceRecording,
                onClickCamera = onClickCamera,
                onClickPhotoOrVideo = onClickPhotoOrVideo,
                photoOrVideoList = topicScreenUiState.photoAndVideo,
                modifier = Modifier,
                messageInput = topicScreenUiState.messageInput,
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
                verticalArrangement = Arrangement.spacedBy(Space16),
                contentPadding = PaddingValues(bottom = Space16)
            ) {
                items(topicScreenUiState.messages.size) {
                    ReplyMessage(
                        messageUiState = topicScreenUiState.messages[it],
                        onAddReactionToMessage = onAddReactionToMessage,
                        onGetNotification =onGetNotification ,
                        onPinMessage = onPinMessage,
                        onSaveMessage =onSaveMessage

                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun TopicContentScreenPreview() {
    TeamixTheme() {
        TopicScreen()
    }
}