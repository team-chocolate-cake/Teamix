package com.chocolate.repository.repository

import com.chocolate.entities.directMessage.DMChat
import com.chocolate.entities.directMessage.DMMessage
import com.chocolate.repository.datastore.remote.DirectMessageRemoteDataSource
import com.chocolate.repository.datastore.remote.MemberRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.DirectMessageRepository
import java.util.Date
import javax.inject.Inject

class DirectMessageRepositoryImpl @Inject constructor(
    private val directMessageRemoteDataSource: DirectMessageRemoteDataSource,
    private val memberRemoteDataSource: MemberRemoteDataSource
) : DirectMessageRepository {
    override suspend fun getChatsByUserId(
        memberId: String,
        currentOrganizationName: String
    ): Flow<List<DMChat>> {
        val chats = directMessageRemoteDataSource.getChatsByUserId(memberId, currentOrganizationName)
        return chats.map {
            it.map {
                val member =
                    memberRemoteDataSource.getMemberInOrganizationById(
                        it.secondMember,
                        currentOrganizationName
                    )
                DMChat(
                    id = it.id,
                    lastMessage = it.lastMessage,
                    lastMessageDate = Date(),
                    name = member?.name ?: "",
                    image = member?.imageUrl ?: ""
                )
            }
        }
    }

    override suspend fun fetchMessagesByGroupId(
        chatId: String,
        currentOrganizationName: String
    ): Flow<List<DMMessage>> {
        return directMessageRemoteDataSource.fetchMessagesByGroupId(chatId, currentOrganizationName)
    }

    override suspend fun sendMessage(
        message: DMMessage,
        currentOrganizationName: String,
        currentChatId: String
    ) {
        directMessageRemoteDataSource.sendMessage(message, currentOrganizationName, currentChatId)
    }

    override suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String {
        return directMessageRemoteDataSource.createGroup(memberIds, currentOrganizationName)
    }
}