package com.chocolate.viewmodel.DMChat

import androidx.lifecycle.SavedStateHandle

class DMChatArgs(savedStateHandle: SavedStateHandle) {
    val groupId: String = checkNotNull(savedStateHandle[GROUP_ID])
    val memberName: String = checkNotNull(savedStateHandle[MEMBER_NAME])

    companion object {
        const val GROUP_ID = "group_id"
        const val MEMBER_NAME = "member_name"
    }
}