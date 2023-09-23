package com.chocolate.viewmodel.createtopic

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateTopicEffect : BaseViewModel.BaseUiEffect {
    data class NavigateToTopicScreen(
        val topicId: String,
        val channelId: String,
        val topicName: String
    ) : CreateTopicEffect

}