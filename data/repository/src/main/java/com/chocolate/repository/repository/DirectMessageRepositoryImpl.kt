package com.chocolate.repository.repository

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import repositories.DirectMessageRepository
import javax.inject.Inject

class DirectMessageRepositoryImpl @Inject constructor(
    private val directMessageRemoteDataSource: DirectMessageRemoteDataSource
) : DirectMessageRepository {
    override suspend fun getChatsByUserId(userid: String): List<Chat> {
        return directMessageRemoteDataSource.getChatsByUserId(userid)
    }

    override suspend fun fetchMessagesByGroupId(groupId: String): List<DMMessage> {
        return directMessageRemoteDataSource.fetchMessagesByGroupId(groupId)
    }

    override suspend fun sendMessage(message: DMMessage, currentGroupId: String) {
        directMessageRemoteDataSource.sendMessage(message , currentGroupId)
    }
}