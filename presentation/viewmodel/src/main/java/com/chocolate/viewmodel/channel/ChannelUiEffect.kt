package com.chocolate.viewmodel.channel

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface ChannelUiEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToTopicDetails(val topicName: String) : ChannelUiEffect
    data class NavigateToCreateTopic(val channelName: String) : ChannelUiEffect
}