package com.chocolate.presentation.screens.channel

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.EmptyDataWithBoxLottie
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.channel.composable.TopicCard
import com.chocolate.presentation.screens.createtopic.navigateToCreateTopic
import com.chocolate.presentation.screens.topicmessages.navigateToTopicMessage
import com.chocolate.presentation.theme.LightCard
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.channel.ChannelInteraction
import com.chocolate.viewmodel.channel.ChannelScreenUiState
import com.chocolate.viewmodel.channel.ChannelUiEffect
import com.chocolate.viewmodel.channel.ChannelViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChannelScreen(
    channelViewModel: ChannelViewModel = hiltViewModel(),
    navController: NavController = LocalNavController.current
) {
    val state by channelViewModel.state.collectAsState()
    CollectUiEffect(channelViewModel.effect) { channelUiEffect ->
        when (channelUiEffect) {
            is ChannelUiEffect.NavigateToTopicDetails -> navController.navigateToTopicMessage(
                channelUiEffect.channelId,
                channelUiEffect.topicId,
                channelUiEffect.topicName
            )

            is ChannelUiEffect.NavigateToCreateTopic -> navController.navigateToCreateTopic(
                channelId = channelUiEffect.channelId
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
    val systemUiController = rememberSystemUiController()
    val isDarkIcons = MaterialTheme.customColors().card == LightCard
    TeamixScaffold(
        hasBackArrow = true,
        title = state.channelName,
        hasAppBar = true,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.customColors().primary,
                onClick = {
                    channelInteraction.onAddTopicClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    tint = MaterialTheme.customColors().onPrimary,
                    contentDescription = ""
                )
            }
        }
    ) { padding ->
        systemUiController.setStatusBarColor(
            MaterialTheme.customColors().card,
            darkIcons = isDarkIcons
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(SpacingXLarge),
            contentPadding = PaddingValues(
                start = SpacingXLarge,
                end = SpacingXLarge,
                bottom = SpacingXLarge
            )
        ) {
            item {
                EmptyDataWithBoxLottie(
                    modifier = Modifier.padding(padding),
                    isPlaying = true,
                    isShow = state.topics.isEmpty() && !state.isLoading,
                    title = stringResource(id = R.string.empty_title_topic),
                    subTitle = stringResource(id = R.string.empty_sub_title_topic)
                )
            }

            items(state.topics.size) {
                val topic = state.topics[it]
                TopicCard(
                    topic = topic,
                    channelId = state.channelId.toInt(),
                    onSeeAll = channelInteraction::onClickSeeAll,
                    onSavedTopic = channelInteraction::onSaveTopic
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