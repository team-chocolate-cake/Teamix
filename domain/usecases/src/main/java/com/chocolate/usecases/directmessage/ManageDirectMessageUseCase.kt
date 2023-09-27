package com.chocolate.usecases.directmessage

import com.chocolate.entities.entity.Chat
import com.chocolate.entities.entity.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.DirectMessageRepository
import javax.inject.Inject


/**
 * made by marwan mahmoud
*/
class ManageDirectMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository
) {

    suspend fun createNewChat(
        userId: List<String>,
    ): String {
        return directMessageRepository.createGroup(userId)
    }

    suspend fun getAllChatsById(
        memberId: String,
    ): Flow<List<Chat>> {
        return directMessageRepository.getChatsByUserId(memberId)
            .map { it.reversed() }
    }

    suspend fun getAllMessagesInChat(
        groupId: String,
    ): Flow<List<Message>> {
        return directMessageRepository.fetchMessagesByGroupId(groupId)
    }

    suspend fun sendMessage(
        message: Message,
        currentChatId: String,
    ) {
        directMessageRepository.sendMessage(message, currentChatId)
    }

    fun searchInDirectMessageChats(chats: List<Chat>, searchQuery: String): List<Chat> {
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