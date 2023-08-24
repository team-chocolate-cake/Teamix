package com.chocolate.viewmodel.createChannel

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface CreateChannelUiEffect : BaseViewModel.BaseUiEffect{
    object NavigationToChooseMembers: CreateChannelUiEffect
}