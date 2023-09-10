package com.chocolate.repository.datastore.remote

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage

interface DirectMessageRemoteDataSource {

    suspend fun getChatsByUserId(userid: String): List<Chat>

    suspend fun fetchMessagesByGroupId(groupId: String) :List<DMMessage>

    suspend fun sendMessage(message:DMMessage, currentGroupId: String)

}