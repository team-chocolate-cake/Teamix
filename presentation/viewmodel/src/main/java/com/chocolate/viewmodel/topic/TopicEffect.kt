package com.chocolate.viewmodel.topic

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface TopicEffect : BaseViewModel.BaseUiEffect{
    object NavigationBack: TopicEffect
}