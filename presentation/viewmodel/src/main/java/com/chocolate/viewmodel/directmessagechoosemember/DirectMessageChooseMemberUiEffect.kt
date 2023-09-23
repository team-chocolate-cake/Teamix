package com.chocolate.viewmodel.directmessagechoosemember

sealed interface DirectMessageChooseMemberUiEffect{
    class NavigateToDmChat(val groupId : String) : DirectMessageChooseMemberUiEffect
}