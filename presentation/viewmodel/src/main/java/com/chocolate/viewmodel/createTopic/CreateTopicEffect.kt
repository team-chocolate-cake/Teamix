package com.chocolate.viewmodel.createTopic

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateTopicEffect : BaseViewModel.BaseUiEffect {
    object NavigateToTopicScreen:CreateTopicEffect

}