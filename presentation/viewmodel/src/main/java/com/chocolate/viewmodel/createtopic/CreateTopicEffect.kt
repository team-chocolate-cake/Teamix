package com.chocolate.viewmodel.createtopic

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateTopicEffect: BaseViewModel.BaseUiEffect {
    data class NavigateToTopicScreen(val topicName: String): CreateTopicEffect
    data class ShowSnackBar(val message: String): CreateTopicEffect
}