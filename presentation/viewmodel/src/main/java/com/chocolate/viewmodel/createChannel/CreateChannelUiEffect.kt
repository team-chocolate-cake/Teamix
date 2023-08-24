package com.chocolate.viewmodel.createChannel

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateChannelUiEffect : BaseViewModel.BaseUiEffect{
    data class NavigationToChooseMembers(val channelName: String): CreateChannelUiEffect
}