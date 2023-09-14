package com.chocolate.usecases.directmessage

import com.chocolate.entities.directMessage.Chat
import javax.inject.Inject

class SearchInDirectMessageChatsUseCase @Inject constructor() {
    private var chats: List<Chat> = emptyList()
    operator fun invoke(searchQuery: String): List<Chat> {
        return if (chats.isNotEmpty() && searchQuery.isNotBlank()) {
            chats.filter {
                it.name.contains(searchQuery, false) || it.lastMessage.contains(
                    searchQuery,
                    false
                )
            }
        } else chats
    }

    fun setChat(chats: List<Chat>) {
        this.chats = chats
    }
}