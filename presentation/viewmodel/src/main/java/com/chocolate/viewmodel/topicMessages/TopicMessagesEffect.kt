package com.chocolate.viewmodel.topicMessages

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface TopicMessagesEffect : BaseViewModel.BaseUiEffect{
    object NavigationBack: TopicMessagesEffect
}

