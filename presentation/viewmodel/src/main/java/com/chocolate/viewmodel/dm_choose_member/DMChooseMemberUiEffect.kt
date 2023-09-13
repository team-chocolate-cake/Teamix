package com.chocolate.viewmodel.dm_choose_member

sealed interface DMChooseMemberUiEffect{
    class NavigateToDmChat(val groupId : String) : DMChooseMemberUiEffect
}