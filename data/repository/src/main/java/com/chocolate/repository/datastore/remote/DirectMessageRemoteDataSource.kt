package com.chocolate.repository.datastore.remote

import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.model.dto.direct_message.Chat
import kotlinx.coroutines.flow.Flow

interface DirectMessageRemoteDataSource {

    suspend fun getChatsByUserId(memberId: String, currentOrganizationName: String): Flow<List<Chat>>
    suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String

    suspend fun sendMessage(message: DMMessage, currentOrganizationName: String, currentChatId: String)

    suspend fun fetchMessagesByGroupId(chatId: String, currentOrganizationName: String): Flow<List<DMMessage>>
}