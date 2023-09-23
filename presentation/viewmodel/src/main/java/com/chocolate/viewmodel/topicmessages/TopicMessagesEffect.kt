package com.chocolate.viewmodel.topicmessages

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface TopicMessagesEffect : BaseViewModel.BaseUiEffect{
    object NavigationBack: TopicMessagesEffect
}

