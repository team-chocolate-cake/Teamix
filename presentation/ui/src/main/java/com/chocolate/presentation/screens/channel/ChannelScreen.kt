package com.chocolate.presentation.screens.channel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.composables.CustomAppBar
import com.chocolate.presentation.screens.channel.composables.Topic
import com.chocolate.presentation.screens.topic_details.ReactionUiState
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import java.util.Date

@Composable
fun ChannelScreen() {
    ChannelContent(
        channelScreenUiState = ChannelScreenUiState(),
        navigationBack = {},
        meetingButtonClick = {},
        onOpenReactTile = {},
        onSeeAll = {},
        onClickReact = { clicked, react ->

        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChannelContent(
    channelScreenUiState: ChannelScreenUiState,
    navigationBack: () -> Unit,
    meetingButtonClick: () -> Unit,
    onOpenReactTile: () -> Unit,
    onSeeAll: () -> Unit,
    onClickReact: (Boolean, ReactionUiState) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.customColors().background,
        topBar = {
            //todo this app bar must be changed to be one composable for all screens
            CustomAppBar(
                title = "Chocolate Team",
                navigationBack = navigationBack,
                actions = {
                    IconButton(
                        onClick = meetingButtonClick
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.meeting_call),
                            tint = MaterialTheme.customColors().onBackground60,
                            contentDescription = "Meeting Call"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.customColors().primary,
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    tint = Color.White,
                    contentDescription = "add button"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(Space16),
            contentPadding = PaddingValues(Space16)
        ) {
            items(channelScreenUiState.topics.size) {
                Topic(
                    topicUiSate = channelScreenUiState.topics[it],
                    onClickReact = onClickReact,
                    onOpenReactTile = onOpenReactTile,
                    onSeeAll = onSeeAll,
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ChannelScreenPreview() {
    TeamixTheme() {
        ChannelScreen()
    }
}