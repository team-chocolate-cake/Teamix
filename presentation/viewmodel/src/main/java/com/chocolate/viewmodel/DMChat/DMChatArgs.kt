package com.chocolate.viewmodel.DMChat

import androidx.lifecycle.SavedStateHandle

class DMChatArgs(savedStateHandle: SavedStateHandle) {
    val memberId: String = checkNotNull(savedStateHandle[MEMBERID])
    val memberName: String = checkNotNull(savedStateHandle[MEMBER_NAME])
    val memberImage: String = checkNotNull(savedStateHandle[MEMBER_IMAGE])

    companion object {
        const val MEMBERID = "member_id"
        const val MEMBER_NAME = "member_name"
        const val MEMBER_IMAGE = "member_image"
    }
}