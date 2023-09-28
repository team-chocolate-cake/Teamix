package com.chocolate.viewmodel.directmessage

import com.chocolate.viewmodel.base.BaseViewModel

sealed interface DirectMessageUiEffect: BaseViewModel.BaseUiEffect{
    object NavigateToChooseMember : DirectMessageUiEffect
    class NavigateToChat(val groupId: String, val name: String) : DirectMessageUiEffect
}