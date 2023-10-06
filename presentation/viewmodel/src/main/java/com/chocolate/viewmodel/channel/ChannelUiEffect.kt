package com.chocolate.viewmodel.channel

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface ChannelUiEffect : BaseViewModel.BaseUiEffect {
    object NavigateToMeeting : ChannelUiEffect
    data class NavigateToTopicDetails(
        val channelId: Int,
        val topicId: String,
        val topicName: String
    ) : ChannelUiEffect

    data class NavigateToCreateTopic(
        val channelId: String,
    ) : ChannelUiEffect
}