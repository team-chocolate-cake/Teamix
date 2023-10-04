package com.chocolate.usecases.repositories

import com.chocolate.entities.entity.Chat
import com.chocolate.entities.entity.Message
import kotlinx.coroutines.flow.Flow

interface DirectMessageRepository {
    suspend fun getChatsByUserId(memberId: String): Flow<List<Chat>>

    suspend fun fetchMessagesByGroupId(chatId: String) : Flow<List<Message>>

    suspend fun sendMessage(message: Message, currentChatId: String)

    suspend fun createGroup(memberIds: List<String>): String
}