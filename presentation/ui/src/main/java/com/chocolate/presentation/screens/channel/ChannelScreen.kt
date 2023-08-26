package com.chocolate.presentation.screens.channel

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.channel.composable.Topic
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.channel.ChannelUiState
import com.chocolate.viewmodel.channel.ChannelViewModel
import com.chocolate.viewmodel.topic.ReactionUiState

@Composable
fun ChannelScreen(viewModel: ChannelViewModel= hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    ChannelContent(
        channelScreenUiState = state,
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
    channelScreenUiState: ChannelUiState,
    meetingButtonClick: () -> Unit,
    onOpenReactTile: () -> Unit,
    onSeeAll: () -> Unit,
    onClickReact: (Boolean, ReactionUiState) -> Unit,
) {
    TeamixScaffold(
        hasBackArrow = true,
        title = channelScreenUiState.channelName,
        isDarkMode = isSystemInDarkTheme(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.customColors().primary,
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    tint = Color.White,
                    contentDescription = ""
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