package com.chocolate.repository.datastore.remote

import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.model.dto.direct_message.Chat
import kotlinx.coroutines.flow.Flow

interface DirectMessageRemoteDataSource {

    suspend fun getChatsByUserId(userid: String, currentOrgName: String): Flow<List<Chat>>
    suspend fun createGroup(userids: List<String>, currentOrgName: String): String

    suspend fun sendMessage(message: DMMessage, currentOrgName: String, currentGroupId: String)

    suspend fun fetchMessagesByGroupId(groupId: String, currentOrgName: String): Flow<List<DMMessage>>
}