package com.chocolate.repository.datastore.remote

import com.chocolate.entities.directMessage.DirectMessage
import com.chocolate.repository.model.dto.directmessage.ChatDto
import kotlinx.coroutines.flow.Flow

interface DirectMessageRemoteDataSource {

    suspend fun getChatsByUserId(memberId: String, currentOrganizationName: String): Flow<List<ChatDto>>
    suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String

    suspend fun sendMessage(directMessage: DirectMessage, currentOrganizationName: String, currentChatId: String)

    suspend fun fetchMessagesByGroupId(chatId: String, currentOrganizationName: String): Flow<List<DirectMessage>>
}