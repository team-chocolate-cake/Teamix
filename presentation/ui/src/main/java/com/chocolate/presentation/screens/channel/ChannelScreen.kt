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
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.composable.Topic
import com.chocolate.presentation.screens.home.LoadingColumn
import com.chocolate.presentation.screens.topic_details.navigateToTopic
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.channel.ChannelInteraction
import com.chocolate.viewmodel.channel.ChannelScreenUiState
import com.chocolate.viewmodel.channel.ChannelUiEffect
import com.chocolate.viewmodel.channel.ChannelViewModel
import com.chocolate.viewmodel.topic.ReactionUiState

@Composable
fun ChannelScreen(
    channelViewModel: ChannelViewModel = hiltViewModel(),
    navController : NavController = LocalNavController.current
) {
    val state by channelViewModel.state.collectAsState()
    CollectUiEffect(viewModel = channelViewModel) { channelUiEffect ->
        when (channelUiEffect) {
            is ChannelUiEffect.NavigateToTopicDetails -> navController.navigateToTopic(channelUiEffect.topicName)
        }
    }

    ChannelContent(
        channelScreenUiState = state,
        channelInteraction = channelViewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChannelContent(
    channelScreenUiState: ChannelScreenUiState,
    channelInteraction: ChannelInteraction
) {
    TeamixScaffold(
        hasBackArrow = true,
        title = channelScreenUiState.channelName,
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
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
        if (channelScreenUiState.isLoading)
            LoadingColumn()
        else
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(Space16),
                contentPadding = PaddingValues(Space16)
            ) {
                items(channelScreenUiState.topics.size) {
                    Topic(
                        topicState = channelScreenUiState.topics[it],
                        onSeeAll = channelInteraction::onClickSeeAll,
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