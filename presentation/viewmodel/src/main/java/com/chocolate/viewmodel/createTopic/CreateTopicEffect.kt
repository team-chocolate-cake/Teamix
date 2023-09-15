package com.chocolate.viewmodel.createTopic

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateTopicEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToTopicScreen(
        val topicId: String,
        val channelId: String,
        val topicName: String
    ) : CreateTopicEffect

}