package com.chocolate.presentation.screens.channel

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.EmptyDataWithBoxLottie
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.channel.composable.Topic
import com.chocolate.presentation.screens.topic_details.navigateToTopic
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.channel.ChannelInteraction
import com.chocolate.viewmodel.channel.ChannelScreenUiState
import com.chocolate.viewmodel.channel.ChannelUiEffect
import com.chocolate.viewmodel.channel.ChannelViewModel

@Composable
fun ChannelScreen(
    channelViewModel: ChannelViewModel = hiltViewModel(),
    navController: NavController = LocalNavController.current
) {
    val state by channelViewModel.state.collectAsState()
    CollectUiEffect(channelViewModel.effect) { channelUiEffect ->
        when (channelUiEffect) {
            is ChannelUiEffect.NavigateToTopicDetails -> navController.navigateToTopic(
               channelUiEffect.channelId,channelUiEffect.topicId, channelUiEffect.topicName
            )
        }
    }

    ChannelContent(
        state = state,
        channelInteraction = channelViewModel
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChannelContent(
    state: ChannelScreenUiState,
    channelInteraction: ChannelInteraction
) {
    TeamixScaffold(
        hasBackArrow = true,
        title = state.channelName,
        isDarkMode = isSystemInDarkTheme(),
        hasAppBar = true,
    ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(SpacingXLarge),
                contentPadding = PaddingValues(SpacingXLarge)
            ) {
                item {
                    EmptyDataWithBoxLottie(
                        modifier = Modifier.padding(padding),
                        isPlaying = true,
                        isShow = state.topics.isEmpty() && !state.isLoading,
                        title ="there is nothing to show" ,
                        subTitle = "Start Create topic and  find it here"
                    )
                }

                items(state.topics.size) {
                    Topic(
                        topicName = state.topics[it].topicName,
                        topicId=state.topics[it].id,
                        channelId=state.channelId.toInt(),
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