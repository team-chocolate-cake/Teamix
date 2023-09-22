package com.chocolate.viewmodel.directMessageChooseMember

sealed interface DirectMessageChooseMemberUiEffect{
    class NavigateToDmChat(val groupId : String) : DirectMessageChooseMemberUiEffect
}