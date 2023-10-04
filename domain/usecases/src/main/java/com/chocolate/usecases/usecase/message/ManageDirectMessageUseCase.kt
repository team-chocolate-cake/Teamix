package com.chocolate.usecases.usecase.message

import com.chocolate.entities.entity.Chat
import com.chocolate.entities.entity.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.chocolate.usecases.repositories.DirectMessageRepository
import com.chocolate.usecases.repositories.SavedLaterRepository
import javax.inject.Inject


/**
 * made by marwan
*/
class ManageDirectMessageUseCase @Inject constructor(
    private val directMessageRepository: DirectMessageRepository,
    private val savedLaterRepository: SavedLaterRepository
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
                it.name.contains(searchQuery, true) || it.lastMessage.contains(
                    searchQuery,
                    true
                )
            }
        } else chats
    }

    suspend fun addSavedLaterMessage(message: Message) =
        savedLaterRepository.saveMessage(message)

    suspend fun getSavedMessages(): Flow<List<Message>> =
        savedLaterRepository.getSavedLaterMessages()

    suspend fun deleteSavedMessageById(savedLaterMessageId: String) =
        savedLaterRepository.deleteSavedLaterMessageById(savedLaterMessageId)
}