package com.chocolate.viewmodel.channel

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface ChannelUiEffect: BaseViewModel.BaseUiEffect{
    data class NavigateToTopicDetails(
        val channelId:Int,
        val topicId:Int,
        val topicName:String
    ) : ChannelUiEffect
}