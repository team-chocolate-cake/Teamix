package com.chocolate.repository.datasource.remote

import com.chocolate.repository.model.dto.message.MessageDto
import com.chocolate.repository.model.dto.directmessage.ChatDto
import kotlinx.coroutines.flow.Flow

interface DirectMessageDataSource {
    suspend fun getChatsByMemberId(
        memberId: String,
        currentOrganizationName: String
    ): Flow<List<ChatDto>>

    suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String

    suspend fun sendMessage(
        messageDto: MessageDto,
        currentOrganizationName: String,
        currentChatId: String
    )

    suspend fun fetchMessagesByGroupId(
        chatId: String,
        currentOrganizationName: String
    ): Flow<List<MessageDto>>
}