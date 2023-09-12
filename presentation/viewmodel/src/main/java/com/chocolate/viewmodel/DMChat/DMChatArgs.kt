package com.chocolate.viewmodel.DMChat

import androidx.lifecycle.SavedStateHandle

class DMChatArgs(savedStateHandle: SavedStateHandle) {
    val memberId: String = checkNotNull(savedStateHandle[MEMBER_ID])
    val memberName: String = checkNotNull(savedStateHandle[MEMBER_NAME])

    companion object {
        const val MEMBER_ID = "member_id"
        const val MEMBER_NAME = "member_name"
    }
}