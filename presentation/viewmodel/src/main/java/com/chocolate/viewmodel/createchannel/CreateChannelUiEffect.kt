package com.chocolate.viewmodel.createchannel

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateChannelUiEffect : BaseViewModel.BaseUiEffect{
    data class NavigationToChooseMembers(
        val channelName: String,
        val description: String?,
    ): CreateChannelUiEffect
}