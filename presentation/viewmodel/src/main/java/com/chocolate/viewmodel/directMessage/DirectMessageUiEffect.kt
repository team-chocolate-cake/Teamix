package com.chocolate.viewmodel.directMessage

sealed interface DirectMessageUiEffect{
    object NavigateToChooseMember : DirectMessageUiEffect
    class NavigateToChat(val groupId: String, val name: String) : DirectMessageUiEffect
}