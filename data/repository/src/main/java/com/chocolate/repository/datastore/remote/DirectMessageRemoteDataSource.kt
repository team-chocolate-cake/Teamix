package com.chocolate.repository.datastore.remote

import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.model.dto.direct_message.Chat

interface DirectMessageRemoteDataSource {

    suspend fun getChatsByUserId(userid: String, currentOrgName: String): List<Chat>

    suspend fun sendMessage(message:DMMessage, currentGroupId: String)

    suspend fun fetchMessagesByGroupId(groupId: String, currentOrgName: String): List<DMMessage>
}