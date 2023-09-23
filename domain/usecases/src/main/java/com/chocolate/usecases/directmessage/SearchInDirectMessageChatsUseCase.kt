package com.chocolate.usecases.directmessage

import com.chocolate.entities.directmessage.Chat
import javax.inject.Inject

class SearchInDirectMessageChatsUseCase @Inject constructor() {

    operator fun invoke(chats: List<Chat>, searchQuery: String): List<Chat> {
        return if (chats.isNotEmpty() && searchQuery.isNotBlank()) {
            chats.filter {
                it.name.contains(searchQuery, false) || it.lastMessage.contains(
                    searchQuery,
                    false
                )
            }
        } else chats
    }

}