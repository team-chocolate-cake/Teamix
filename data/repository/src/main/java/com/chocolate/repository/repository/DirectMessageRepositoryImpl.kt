package com.chocolate.repository.repository

import com.chocolate.entities.directMessage.DMChat
import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.datastore.remote.MemberRemoteDataSource
import repositories.DirectMessageRepository
import java.util.Date
import javax.inject.Inject

class DirectMessageRepositoryImpl @Inject constructor(
    private val directMessageRemoteDataSource: DirectMessageRemoteDataSource,
    private val memberRemoteDataSource: MemberRemoteDataSource
) : DirectMessageRepository {
    override suspend fun getChatsByUserId(userid: String, currentOrgName: String): List<DMChat> {
        val chats = directMessageRemoteDataSource.getChatsByUserId(userid, currentOrgName)
        return chats.map {
            val member =
                memberRemoteDataSource.getMemberInOrganizationById(it.secondMember, currentOrgName)
            DMChat(
                id = it.id,
                lastMessage = it.lastMessage,
                lastMessageDate = Date(),
                name = member?.name ?: "",
                image = member?.imageUrl ?: ""
            )
        }
    }

    override suspend fun fetchMessagesByGroupId(
        groupId: String,
        currentOrgName: String
    ): List<DMMessage> {
        return directMessageRemoteDataSource.fetchMessagesByGroupId(groupId, currentOrgName)
    }

    override suspend fun sendMessage(message: DMMessage, currentGroupId: String) {
        directMessageRemoteDataSource.sendMessage(message, currentGroupId)
    }
}