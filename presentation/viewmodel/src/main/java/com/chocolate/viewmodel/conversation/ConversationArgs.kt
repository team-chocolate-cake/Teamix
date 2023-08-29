package com.chocolate.viewmodel.conversation

import androidx.lifecycle.SavedStateHandle

class ConversationArgs(savedStateHandle: SavedStateHandle) {
    val friendId: String = checkNotNull(savedStateHandle[FRIEND_ID])

    companion object {
        const val FRIEND_ID = "FRIEND_ID"
    }
}