package com.chocolate.repository.repository

import com.chocolate.entities.directMessage.Chat
import com.chocolate.entities.directMessage.DirectMessage
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
    ): Flow<List<Chat>> {
        val chats = directMessageRemoteDataSource.getChatsByUserId(memberId, currentOrganizationName)
        return chats.map {
            it.map {
                val member =
                    memberRemoteDataSource.getMemberInOrganizationById(
                        it.secondMemberId,
                        currentOrganizationName
                    )
                Chat(
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
    ): Flow<List<DirectMessage>> {
        return directMessageRemoteDataSource.fetchMessagesByGroupId(chatId, currentOrganizationName)
    }

    override suspend fun sendMessage(
        directMessage: DirectMessage,
        currentOrganizationName: String,
        currentChatId: String
    ) {
        directMessageRemoteDataSource.sendMessage(directMessage, currentOrganizationName, currentChatId)
    }

    override suspend fun createGroup(memberIds: List<String>, currentOrganizationName: String): String {
        return directMessageRemoteDataSource.createGroup(memberIds, currentOrganizationName)
    }
}